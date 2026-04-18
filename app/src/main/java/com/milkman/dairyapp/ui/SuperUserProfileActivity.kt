package com.milkman.dairyapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.milkman.dairyapp.R
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.AdminDetailsResponse
import com.milkman.dairyapp.network.models.AdminResponse
import com.milkman.dairyapp.network.models.CreateAdminRequest
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class SuperUserProfileActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddAdmin: MaterialButton
    private lateinit var btnLogout: MaterialButton
    private var adminList = mutableListOf<AdminResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_user_profile)

        // Super user full view should not be scoped by default.
        ApiClient.clearAdminScope()

        sessionManager = SessionManager(this)
        recyclerView = findViewById(R.id.recyclerViewAdmins)
        btnAddAdmin = findViewById(R.id.btnAddAdmin)
        btnLogout = findViewById(R.id.btnLogout)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdminAdapter(
            admins = adminList,
            onAdminClick = { admin -> showAdminDetailsDialog(admin) },
            onSwitchClick = { admin -> switchToAdmin(admin) },
            onDeleteClick = { admin -> confirmDeleteAdmin(admin) }
        )

        btnAddAdmin.setOnClickListener { showAddAdminDialog() }
        btnLogout.setOnClickListener { logout() }

        loadAdmins()
    }

    private fun loadAdmins() {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val admins = ApiClient.authService.getAdmins("Bearer $token")
                adminList.clear()
                adminList.addAll(admins)
                withContext(Dispatchers.Main) {
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SuperUserProfileActivity, "Error loading admins: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showAdminDetailsDialog(admin: AdminResponse) {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val details = ApiClient.authService.getAdminDetails(admin.id, "Bearer $token")

                withContext(Dispatchers.Main) {
                    showAdminDetailsDialogInternal(details)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@SuperUserProfileActivity,
                        "Error loading admin details: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showAdminDetailsDialogInternal(admin: AdminDetailsResponse) {
        val permissionsText = if (admin.permissions.isEmpty()) {
            "No permission data available"
        } else {
            admin.permissions.toSortedMap().entries.joinToString("\n") { entry ->
                "- ${formatPermissionKey(entry.key)}: ${if (entry.value) "Yes" else "No"}"
            }
        }

        val usersPreview = if (admin.assignedUsers.isEmpty()) {
            "None"
        } else {
            admin.assignedUsers.take(8).joinToString("\n") {
                "- ${it.fullName} (${it.username}) - ${it.role}"
            } + if (admin.assignedUsers.size > 8) {
                "\n- ... and ${admin.assignedUsers.size - 8} more"
            } else {
                ""
            }
        }

        val customersPreview = if (admin.assignedCustomers.isEmpty()) {
            "None"
        } else {
            admin.assignedCustomers.take(8).joinToString("\n") {
                "- ${it.name} (${it.type ?: "Unknown"})"
            } + if (admin.assignedCustomers.size > 8) {
                "\n- ... and ${admin.assignedCustomers.size - 8} more"
            } else {
                ""
            }
        }

        val createdByText = admin.createdBy?.let {
            "${it.fullName} (${it.username})"
        } ?: "System"

        val details = """
            Full Name: ${admin.fullName}
            Username: ${admin.username}
            Role: ${admin.role}
            Status: ${if (admin.isActive) "Active" else "Inactive"}
            Created By: $createdByText
            Created At: ${admin.createdAt ?: "N/A"}
            Updated At: ${admin.updatedAt ?: "N/A"}

            Assigned Users (${admin.assignedUsersCount}):
            $usersPreview

            Assigned Customers (${admin.assignedCustomersCount}):
            $customersPreview

            Permissions:
            $permissionsText
        """.trimIndent()

        MaterialAlertDialogBuilder(this)
            .setTitle("Admin Details")
            .setMessage(details)
            .setPositiveButton("Close", null)
            .show()
    }

    private fun showAddAdminDialog() {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle("Add New Admin")

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val etUsername = TextInputEditText(this).apply {
            hint = "Username"
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 }
        }

        val etPassword = TextInputEditText(this).apply {
            hint = "Password"
            inputType = android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 }
        }

        val etFullName = TextInputEditText(this).apply {
            hint = "Full Name"
            layoutParams = android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        layout.addView(etUsername)
        layout.addView(etPassword)
        layout.addView(etFullName)

        builder.setView(layout)
        builder.setPositiveButton("Create Admin") { _, _ ->
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val fullName = etFullName.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                createAdmin(username, password, fullName)
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    private fun createAdmin(username: String, password: String, fullName: String) {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val request = CreateAdminRequest(username, password, fullName)
                val response = ApiClient.staffService.createAdmin("Bearer $token", request)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@SuperUserProfileActivity, "Admin created successfully", Toast.LENGTH_SHORT).show()
                        loadAdmins()
                    } else {
                        Toast.makeText(
                            this@SuperUserProfileActivity,
                            parseErrorMessage(response.errorBody()?.string(), "Unable to create admin"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SuperUserProfileActivity, "Error creating admin: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun confirmDeleteAdmin(admin: AdminResponse) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete Admin")
            .setMessage("Delete ${admin.fullName} (${admin.username})?")
            .setPositiveButton("Delete") { _, _ ->
                deleteAdmin(admin)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteAdmin(admin: AdminResponse) {
        lifecycleScope.launch {
            try {
                val token = sessionManager.getToken() ?: return@launch
                val response = ApiClient.staffService.deleteStaff(admin.id, "Bearer $token")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@SuperUserProfileActivity,
                            "Admin deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadAdmins()
                    } else {
                        Toast.makeText(
                            this@SuperUserProfileActivity,
                            parseErrorMessage(response.errorBody()?.string(), "Unable to delete admin"),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SuperUserProfileActivity, "Error deleting admin: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun parseErrorMessage(rawBody: String?, fallbackMessage: String): String {
        if (rawBody.isNullOrBlank()) {
            return fallbackMessage
        }

        return try {
            val json = JSONObject(rawBody)
            val message = json.optString("message", fallbackMessage).trim()
            if (message.isBlank()) fallbackMessage else message
        } catch (_: Exception) {
            fallbackMessage
        }
    }

    private fun formatPermissionKey(rawKey: String): String {
        if (rawKey.isBlank()) {
            return rawKey
        }

        val spaced = rawKey.replace(Regex("([a-z])([A-Z])"), "$1 $2")
        return spaced.substring(0, 1).uppercase() + spaced.substring(1)
    }

    private fun switchToAdmin(admin: AdminResponse) {
        val token = sessionManager.getToken()
        val currentUserId = sessionManager.userId()

        if (token.isNullOrBlank() || currentUserId.isBlank()) {
            Toast.makeText(this, "Invalid session. Please login again.", Toast.LENGTH_SHORT).show()
            return
        }

        // Keep internal user id stable for existing local DB flows and switch role context.
        sessionManager.saveSession(
            userId = currentUserId,
            username = admin.username,
            role = AppConstants.ROLE_ADMIN,
            fullName = admin.fullName,
            token = token
        )
        ApiClient.setAdminScope(admin.id)

        Toast.makeText(this, "Switched to ${admin.fullName}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AdminDashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    private fun logout() {
        ApiClient.clearAdminScope()
        sessionManager.clearSession()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}

class AdminAdapter(
    private val admins: List<AdminResponse>,
    private val onAdminClick: (AdminResponse) -> Unit,
    private val onSwitchClick: (AdminResponse) -> Unit,
    private val onDeleteClick: (AdminResponse) -> Unit
) : RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {

    class AdminViewHolder(
        itemView: android.view.View,
        private val onAdminClick: (AdminResponse) -> Unit,
        private val onSwitchClick: (AdminResponse) -> Unit,
        private val onDeleteClick: (AdminResponse) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(admin: AdminResponse) {
            itemView.findViewById<android.widget.TextView>(R.id.tvAdminUsername).text = "Username: ${admin.username}"
            itemView.findViewById<android.widget.TextView>(R.id.tvAdminName).text = "Name: ${admin.fullName}"
            itemView.findViewById<android.widget.TextView>(R.id.tvAdminStatus).text =
                "${if (admin.isActive) "Active" else "Inactive"} | Users: ${admin.assignedUsersCount} | Customers: ${admin.assignedCustomersCount}"

            itemView.setOnClickListener { onAdminClick(admin) }
            itemView.findViewById<MaterialButton>(R.id.btnSwitchAdmin).setOnClickListener {
                onSwitchClick(admin)
            }
            itemView.findViewById<MaterialButton>(R.id.btnDeleteAdmin).setOnClickListener {
                onDeleteClick(admin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_admin, parent, false)
        return AdminViewHolder(view, onAdminClick, onSwitchClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.bind(admins[position])
    }

    override fun getItemCount() = admins.size
}

