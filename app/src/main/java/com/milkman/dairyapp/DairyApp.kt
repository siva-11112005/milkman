package com.milkman.dairyapp

import android.app.Application
import android.content.Intent
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.service.PeriodicSyncService

class DairyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Warm up Room singleton once at app startup.
        AppDatabase.getInstance(this)
        
        // Start periodic sync service
        startService(Intent(this, PeriodicSyncService::class.java))
    }
}
