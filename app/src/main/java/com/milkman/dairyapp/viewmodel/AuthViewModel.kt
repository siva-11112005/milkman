package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.model.User
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.AuthRepository
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.LoginRequest
import kotlinx.coroutines.launch

data class LoginUiState(
    val success: Boolean,
    val message: String,
    val user: User? = null,
    val token: String? = null
)

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = AuthRepository(db.userDao(), AuditRepository(db.auditLogDao()))

    private val _loginState = MutableLiveData<LoginUiState>()
    val loginState: LiveData<LoginUiState> = _loginState

    init {
        viewModelScope.launch {
            repository.ensureDefaultAdmin()
        }
    }

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _loginState.value = LoginUiState(false, "Username and password are required")
            return
        }

        viewModelScope.launch {
            try {
                val request = LoginRequest(username.trim(), password)
                val response = ApiClient.authService.login(request)
                
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    val user = User(
                        id = loginResponse.user.id.toIntOrNull() ?: 0,
                        username = loginResponse.user.username,
                        passwordHash = "",
                        fullName = loginResponse.user.fullName,
                        role = loginResponse.user.role
                    )
                    _loginState.postValue(
                        LoginUiState(
                            success = true,
                            message = "Login successful",
                            user = user,
                            token = loginResponse.token
                        )
                    )
                } else {
                    _loginState.postValue(LoginUiState(false, "Invalid credentials"))
                }
            } catch (e: Exception) {
                _loginState.postValue(LoginUiState(false, "Login failed: ${e.message}"))
            }
        }
    }
}
