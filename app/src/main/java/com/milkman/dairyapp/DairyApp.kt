package com.milkman.dairyapp

import android.app.Application
import android.content.Intent
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.service.PeriodicSyncService
import com.milkman.dairyapp.util.SessionManager

class DairyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Warm up Room singleton once at app startup.
        AppDatabase.getInstance(this)

        // Restore optional acting-admin scope for super user switched mode.
        val sessionManager = SessionManager(this)
        ApiClient.setAdminScope(sessionManager.actingAdminId())
        
        // Start periodic sync service
        startService(Intent(this, PeriodicSyncService::class.java))
    }
}
