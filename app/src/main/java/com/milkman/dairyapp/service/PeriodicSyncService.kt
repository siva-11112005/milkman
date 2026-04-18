package com.milkman.dairyapp.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PeriodicSyncService : Service() {
    private lateinit var syncService: DataSyncService
    private val binder = LocalBinder()
    private var syncJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    companion object {
        private const val SYNC_INTERVAL_MS = 60000L // 1 minute
        const val TAG = "PeriodicSyncService"
    }

    inner class LocalBinder : Binder() {
        fun getService(): PeriodicSyncService = this@PeriodicSyncService
    }

    override fun onCreate() {
        super.onCreate()
        syncService = DataSyncService(this)
        Log.d(TAG, "✓ PeriodicSyncService created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "📡 Starting periodic sync every 1 minute")
        startPeriodicSync()
        return START_STICKY
    }

    private fun startPeriodicSync() {
        if (syncJob?.isActive == true) {
            Log.d(TAG, "Sync job already running")
            return
        }

        syncJob = scope.launch(Dispatchers.IO) {
            while (true) {
                try {
                    Log.d(TAG, "⏱️ Running sync cycle...")
                    val result = syncService.syncPendingOperations()
                    
                    if (result.syncedCount > 0) {
                        Log.d(TAG, "✓ Synced ${result.syncedCount} items to MongoDB Atlas")
                    }
                    
                    if (!result.success && result.message.contains("Failed:") && !result.message.contains("Failed: 0")) {
                        Log.w(TAG, "⚠️ Sync issues: ${result.message}")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Sync error in periodic task", e)
                }
                
                // Wait 1 minute before next sync
                delay(SYNC_INTERVAL_MS)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? = binder

    override fun onDestroy() {
        super.onDestroy()
        syncJob?.cancel()
        scope.coroutineContext[Job]?.cancel()
        Log.d(TAG, "PeriodicSyncService destroyed")
    }
}
