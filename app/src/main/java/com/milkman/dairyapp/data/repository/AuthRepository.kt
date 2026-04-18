package com.milkman.dairyapp.data.repository

import com.milkman.dairyapp.data.dao.UserDao
import com.milkman.dairyapp.data.model.User
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.LoginRequest
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.PasswordUtils

class AuthRepository(
    private val userDao: UserDao,
    private val auditRepository: AuditRepository
) {
    suspend fun ensureDefaultAdmin() {
        val admin = userDao.getUserByUsername("admin")
        if (admin == null) {
            val newId = userDao.insert(
                User(
                    username = "admin",
                    passwordHash = PasswordUtils.hash("admin123"),
                    fullName = "Milkman Admin",
                    role = AppConstants.ROLE_ADMIN
                )
            ).toInt()
            auditRepository.log("users", newId, "CREATE", newId, "Default admin account created")
        }
    }

    suspend fun login(username: String, password: String): User? {
        return try {
            val request = LoginRequest(username.trim(), password)
            val response = ApiClient.authService.login(request)
            
            if (response.isSuccessful && response.body() != null) {
                val loginResponse = response.body()!!
                val userResponse = loginResponse.user
                
                // Convert network response to local User model
                User(
                    id = userResponse.id.toIntOrNull() ?: 0,
                    username = userResponse.username,
                    passwordHash = "", // Not needed since we're using JWT token
                    fullName = userResponse.fullName,
                    role = userResponse.role
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
