package com.milkman.dairyapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.milkman.dairyapp.databinding.ActivityLoginBinding
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.service.DataSyncService
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var syncService: DataSyncService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        syncService = DataSyncService(this)
        sessionManager.clearActingAdmin()
        ApiClient.clearAdminScope()
        
        if (sessionManager.isLoggedIn()) {
            // Sync pending operations when app is opened with existing session
            lifecycleScope.launch {
                syncPendingOperations()
            }
            openDashboard()
            return
        }

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text?.toString().orEmpty()
            val password = binding.etPassword.text?.toString().orEmpty()
            viewModel.login(username, password)
        }

        viewModel.loginState.observe(this) { state ->
            Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            if (state.success && state.user != null) {
                sessionManager.clearActingAdmin()
                ApiClient.clearAdminScope()
                sessionManager.saveSession(
                    userId = state.user.id.toString(),
                    username = state.user.username,
                    role = state.user.role,
                    fullName = state.user.fullName,
                    token = state.token.orEmpty()
                )
                
                // Sync pending operations after successful login
                lifecycleScope.launch {
                    syncPendingOperations()
                }
                
                if (state.user.role == AppConstants.ROLE_SUPER_USER || state.user.role == AppConstants.ROLE_ADMIN) {
                    openAdminDashboard()
                } else {
                    openCustomerDashboard()
                }
            }
        }
    }

    private suspend fun syncPendingOperations() {
        try {
            val result = syncService.syncPendingOperations()
            if (result.syncedCount > 0) {
                Toast.makeText(
                    this,
                    "📡 Synced ${result.syncedCount} changes to MongoDB Atlas ✅",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (result.message.contains("Failed:") && !result.message.contains("Failed: 0")) {
                Toast.makeText(
                    this,
                    "⚠️ ${result.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            // Silently fail - sync is not critical for app functionality
            android.util.Log.e("LoginActivity", "Sync failed", e)
        }
    }

    private fun openAdminDashboard() {
        startActivity(Intent(this, AdminDashboardActivity::class.java))
        finish()
    }

    private fun openCustomerDashboard() {
        startActivity(Intent(this, CustomerDashboardActivity::class.java))
        finish()
    }

    private fun openDashboard() {
        // This method is now obsolete, but we'll keep it for now
        // to avoid breaking anything.
        if (sessionManager.getRole() == AppConstants.ROLE_ADMIN) {
            openAdminDashboard()
        } else {
            openCustomerDashboard()
        }
    }
}
