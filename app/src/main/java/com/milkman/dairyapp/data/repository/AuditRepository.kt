package com.milkman.dairyapp.data.repository

import androidx.lifecycle.LiveData
import com.milkman.dairyapp.data.dao.AuditLogDao
import com.milkman.dairyapp.data.entity.AuditLogEntity
import com.milkman.dairyapp.util.TimeUtils

class AuditRepository(private val auditLogDao: AuditLogDao) {
    fun getRecentLogs(limit: Int): LiveData<List<AuditLogEntity>> = auditLogDao.getRecentLogs(limit)

    suspend fun log(tableName: String, recordId: Int, action: String, userId: Int, details: String) {
        auditLogDao.insert(
            AuditLogEntity(
                tableName = tableName,
                recordId = recordId,
                action = action,
                userId = userId,
                details = details,
                createdAt = TimeUtils.currentTimestamp()
            )
        )
    }
}
