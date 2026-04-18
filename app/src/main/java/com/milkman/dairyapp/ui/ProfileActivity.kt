package com.milkman.dairyapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.milkman.dairyapp.R
import com.milkman.dairyapp.databinding.ActivityProfileBinding
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.AdminDetailsResponse
import com.milkman.dairyapp.network.models.AdminResponse
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionManager: SessionManager
    private var adminList = mutableListOf<AdminResponse>()
    private var selectedAdmin: AdminResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val isSuperUser = sessionManager.getRole() == AppConstants.ROLE_SUPER_USER
        val isAdmin = sessionManager.getRole() == AppConstants.ROLE_ADMIN
        binding.tvProfileRole.text = "Role: ${sessionManager.getRole()}"

        // Show admin selector for super users
        if (isSuperUser) {
            loadAdminsForSelector()
        }

        viewModel.profile.observe(this) { user ->
            if (user != null) {
                binding.tvProfileUsername.text = user.username
                binding.etProfileFullName.setText(user.fullName)
                binding.etProfilePhone.setText(user.phone.orEmpty())
                binding.etProfileAddress.setText(user.address.orEmpty())
                binding.etProfilePrice.setText(user.pricePerLiter?.toString().orEmpty())

                binding.tilProfilePrice.isEnabled = isAdmin
                if (!isAdmin) {
                    binding.tilProfilePrice.helperText = "Price can be changed only by admin"
                    binding.etProfilePrice.isEnabled = false
                } else {
                    binding.tilProfilePrice.helperText = "Admin can edit this field"
                    binding.etProfilePrice.isEnabled = true
                }
            }
        }

        viewModel.actionState.observe(this) { result ->
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            if (result.success) {
                val currentName = binding.etProfileFullName.text?.toString().orEmpty()
                sessionManager.saveSession(
                    userId = sessionManager.userId(),
                    username = sessionManager.username(),
                    role = sessionManager.getRole(),
                    fullName = currentName,
                    token = sessionManager.getToken().orEmpty()
                )
                binding.etCurrentPassword.setText("")
                binding.etNewPassword.setText("")
            }
        }

        binding.btnSaveProfile.setOnClickListener {
            val fullName = binding.etProfileFullName.text?.toString().orEmpty()
            val phone = binding.etProfilePhone.text?.toString().orEmpty()
            val address = binding.etProfileAddress.text?.toString().orEmpty()
            val price = binding.etProfilePrice.text?.toString().orEmpty().toDoubleOrNull()

            if (fullName.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this, "Name, phone and address are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isAdmin && binding.etProfilePrice.text?.isNotBlank() == true && price == null) {
                Toast.makeText(this, "Enter valid price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.updateProfile(
                userId = sessionManager.userId().toInt(),
                fullName = fullName,
                phone = phone,
                address = address,
                pricePerLiter = if (isAdmin) price else null,
                actorUserId = sessionManager.userId().toInt(),
                canEditPrice = isAdmin
            )
        }

        binding.btnChangePassword.setOnClickListener {
            val currentPassword = binding.etCurrentPassword.text?.toString().orEmpty()
            val newPassword = binding.etNewPassword.text?.toString().orEmpty()
            if (currentPassword.isBlank() || newPassword.length < 6) {
                Toast.makeText(this, "Use current password and a new password with 6+ chars", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.changePassword(
                userId = sessionManager.userId().toInt(),
                currentPassword = currentPassword,
                newPassword = newPassword,
                actorUserId = sessionManager.userId().toInt()
            )
        }

        binding.btnSwitchToAdmin.setOnClickListener {
            selectedAdmin?.let { admin ->
                switchToAdmin(admin)
            }
        }

        binding.btnLogout.setOnClickListener {
            sessionManager.clearSession()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        viewModel.loadProfile(sessionManager.userId().toInt())
    }

    private fun loadAdminsForSelector() {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val admins = ApiClient.authService.getAdmins("Bearer $token")
                adminList.clear()
                adminList.addAll(admins)

                withContext(Dispatchers.Main) {
                    val adminNames = admins.map { "${it.fullName} (${it.username})" }
                    val adapter = ArrayAdapter(
                        this@ProfileActivity,
                        android.R.layout.simple_list_item_1,
                        adminNames
                    )
                    
                    val actvAdminSelector = findViewById<AutoCompleteTextView>(R.id.actvAdminSelector)
                    actvAdminSelector.setAdapter(adapter)

                    actvAdminSelector.setOnItemClickListener { _, _, position, _ ->
                        selectedAdmin = adminList.getOrNull(position)
                        selectedAdmin?.let { admin ->
                            loadAdminDetails(admin)
                        }
                    }

                    // Show admin selector
                    findViewById<TextInputLayout>(R.id.tilAdminSelector).visibility = android.view.View.VISIBLE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error loading admins: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun loadAdminDetails(admin: AdminResponse) {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val details = ApiClient.authService.getAdminDetails(admin.id, "Bearer $token")

                withContext(Dispatchers.Main) {
                    showAdminDetails(details)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Error loading admin details: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showAdminDetails(admin: AdminDetailsResponse) {
        val tvAdminName = findViewById<android.widget.TextView>(R.id.tvAdminName)
        val tvAdminStatus = findViewById<android.widget.TextView>(R.id.tvAdminStatus)
        val tvAdminCreated = findViewById<android.widget.TextView>(R.id.tvAdminCreated)
        val llAdminDetails = findViewById<android.widget.LinearLayout>(R.id.llAdminDetails)

        val permissionsSummary = if (admin.permissions.isEmpty()) {
            "No permission data"
        } else {
            admin.permissions.toSortedMap().entries.joinToString("\n") {
                "- ${formatPermissionKey(it.key)}: ${if (it.value) "Yes" else "No"}"
            }
        }

        val usersPreview = if (admin.assignedUsers.isEmpty()) {
            "None"
        } else {
            admin.assignedUsers.take(6).joinToString("\n") {
                "- ${it.fullName} (${it.username}) - ${it.role}"
            } + if (admin.assignedUsers.size > 6) {
                "\n- ... and ${admin.assignedUsers.size - 6} more"
            } else {
                ""
            }
        }

        val customersPreview = if (admin.assignedCustomers.isEmpty()) {
            "None"
        } else {
            admin.assignedCustomers.take(6).joinToString("\n") {
                "- ${it.name} (${it.type ?: "Unknown"})"
            } + if (admin.assignedCustomers.size > 6) {
                "\n- ... and ${admin.assignedCustomers.size - 6} more"
            } else {
                ""
            }
        }

        val createdByText = admin.createdBy?.let {
            "${it.fullName} (${it.username})"
        } ?: "System"

        tvAdminName.text = "${admin.fullName} (${admin.username})"
        tvAdminStatus.text =
            "Status: ${if (admin.isActive) "Active" else "Inactive"} | " +
                "Users: ${admin.assignedUsersCount} | Customers: ${admin.assignedCustomersCount}"
        tvAdminCreated.text =
            "Created: ${admin.createdAt ?: "N/A"}\n" +
                "Updated: ${admin.updatedAt ?: "N/A"}\n" +
                "Created By: $createdByText\n\n" +
                "Permissions:\n$permissionsSummary\n\n" +
                "Assigned Users:\n$usersPreview\n\n" +
                "Assigned Customers:\n$customersPreview"

        llAdminDetails.visibility = android.view.View.VISIBLE
    }

    private fun formatPermissionKey(rawKey: String): String {
        if (rawKey.isBlank()) {
            return rawKey
        }

        val spaced = rawKey.replace(Regex("([a-z])([A-Z])"), "$1 $2")
        return spaced.substring(0, 1).uppercase() + spaced.substring(1)
    }

    private fun switchToAdmin(admin: AdminResponse) {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                
                // Get admin details from backend
                val adminDetails = ApiClient.authService.getAdmins("Bearer $token")
                    .firstOrNull { it.id == admin.id }
                
                if (adminDetails != null) {
                    // Save session with admin details
                    sessionManager.saveSession(
                        userId = adminDetails.id.toString(),
                        username = adminDetails.username,
                        role = AppConstants.ROLE_ADMIN,
                        fullName = adminDetails.fullName,
                        token = token
                    )

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "🔄 Switched to ${admin.fullName}",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Navigate to admin dashboard
                        val intent = Intent(this@ProfileActivity, AdminDashboardActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileActivity, "Admin not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProfileActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
