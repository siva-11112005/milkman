package com.milkman.dairyapp.util

import android.content.Context
import com.milkman.dairyapp.util.AppConstants

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("milkman_session", Context.MODE_PRIVATE)

    fun saveSession(userId: String, username: String, role: String, fullName: String, token: String = "") {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_USER_ID, userId)
            .putString(KEY_USERNAME, username)
            .putString(KEY_ROLE, role)
            .putString(KEY_FULL_NAME, fullName)
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    fun setActingAdmin(adminId: String, adminName: String) {
        prefs.edit()
            .putString(KEY_ACTING_ADMIN_ID, adminId)
            .putString(KEY_ACTING_ADMIN_NAME, adminName)
            .apply()
    }

    fun clearActingAdmin() {
        prefs.edit()
            .remove(KEY_ACTING_ADMIN_ID)
            .remove(KEY_ACTING_ADMIN_NAME)
            .apply()
    }

    fun actingAdminId(): String? = prefs.getString(KEY_ACTING_ADMIN_ID, null)

    fun actingAdminName(): String? = prefs.getString(KEY_ACTING_ADMIN_NAME, null)

    fun isActingAsAdmin(): Boolean = !actingAdminId().isNullOrBlank()

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)

    fun userId(): String = prefs.getString(KEY_USER_ID, "") ?: ""

    fun username(): String = prefs.getString(KEY_USERNAME, "") ?: ""

    fun fullName(): String = prefs.getString(KEY_FULL_NAME, "") ?: ""

    fun getRole(): String = prefs.getString(KEY_ROLE, "") ?: ""

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun isAdmin(): Boolean = getRole() == AppConstants.ROLE_ADMIN

    fun isSuperUser(): Boolean = getRole() == AppConstants.ROLE_SUPER_USER

    fun canManageUsersAndCustomers(): Boolean = isAdmin() || isSuperUser()

    companion object {
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_ROLE = "role"
        private const val KEY_FULL_NAME = "full_name"
        private const val KEY_TOKEN = "token"
        private const val KEY_ACTING_ADMIN_ID = "acting_admin_id"
        private const val KEY_ACTING_ADMIN_NAME = "acting_admin_name"
    }
}
