package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.AuditLogEntity
import com.milkman.dairyapp.data.repository.AuditRepository

class AuditLogViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = AuditRepository(db.auditLogDao())

    val recentLogs: LiveData<List<AuditLogEntity>> = repository.getRecentLogs(200)
}
