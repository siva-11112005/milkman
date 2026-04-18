package com.milkman.dairyapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "sync_queue")
data class SyncQueueEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val operationType: String, // "ADD_SUPPLIER", "ADD_BUYER", "UPDATE_SUPPLIER", etc.
    val tableName: String,
    val recordId: Int = 0,
    val payload: String, // JSON string of the operation data
    val createdAt: Long = System.currentTimeMillis(),
    val synced: Boolean = false,
    val syncAttempts: Int = 0,
    val lastSyncError: String? = null
)
