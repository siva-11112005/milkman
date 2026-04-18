package com.milkman.dairyapp.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "audit_logs",
    indices = [Index(value = ["tableName", "recordId"]), Index(value = ["createdAt"])]
)
data class AuditLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tableName: String,
    val recordId: Int,
    val action: String,
    val userId: Int,
    val details: String,
    val createdAt: Long
)
