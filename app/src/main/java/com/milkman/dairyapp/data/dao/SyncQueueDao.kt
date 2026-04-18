package com.milkman.dairyapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.milkman.dairyapp.data.entity.SyncQueueEntity

@Dao
interface SyncQueueDao {
    @Insert
    suspend fun insert(item: SyncQueueEntity): Long

    @Delete
    suspend fun delete(item: SyncQueueEntity)

    @Query("SELECT * FROM sync_queue WHERE synced = 0 ORDER BY createdAt ASC")
    suspend fun getPendingSync(): List<SyncQueueEntity>

    @Query("SELECT * FROM sync_queue WHERE synced = 0 ORDER BY createdAt ASC")
    fun getPendingSyncLive(): LiveData<List<SyncQueueEntity>>

    @Query("UPDATE sync_queue SET synced = 1 WHERE id = :id")
    suspend fun markAsSynced(id: String)

    @Query("UPDATE sync_queue SET syncAttempts = syncAttempts + 1, lastSyncError = :error WHERE id = :id")
    suspend fun recordSyncError(id: String, error: String)

    @Query("DELETE FROM sync_queue WHERE synced = 1")
    suspend fun clearSyncedItems()

    @Query("SELECT COUNT(*) FROM sync_queue WHERE synced = 0")
    suspend fun getPendingCount(): Int
}
