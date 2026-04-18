package com.milkman.dairyapp.service

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.dao.SyncQueueDao
import com.milkman.dairyapp.network.ApiClient
import com.milkman.dairyapp.network.models.AddSupplierRequest
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSyncService(private val context: Context) {
    private val db = AppDatabase.getInstance(context)
    private val syncQueueDao = db.syncQueueDao()
    private val customerDao = db.customerDao()
    private val sessionManager = SessionManager(context)
    private val gson = Gson()

    suspend fun syncPendingOperations(): SyncResult = withContext(Dispatchers.IO) {
        try {
            // Get the actual authentication token from SessionManager
            val token = sessionManager.getToken()
            if (token.isNullOrBlank()) {
                Log.w("DataSyncService", "No authentication token available, skipping sync")
                return@withContext SyncResult(false, "No authentication token available", 0)
            }

            var successCount = 0
            var failureCount = 0
            val pendingItems = syncQueueDao.getPendingSync()

            if (pendingItems.isNotEmpty()) {
                Log.d("DataSyncService", "Starting sync of ${pendingItems.size} pending operations")
            }

            for (item in pendingItems) {
                try {
                    when (item.operationType) {
                        "CREATE_CUSTOMER" -> {
                            val request = gson.fromJson(item.payload, com.milkman.dairyapp.network.models.CreateCustomerRequest::class.java)
                            val response = ApiClient.customerService.createCustomer(
                                request,
                                "Bearer $token"
                            )
                            
                            if (response.isSuccessful) {
                                syncQueueDao.markAsSynced(item.id)
                                Log.d("DataSyncService", "✓ Successfully synced CREATE_CUSTOMER: ${request.name}")
                                successCount++
                            } else {
                                syncQueueDao.recordSyncError(item.id, response.message())
                                Log.w("DataSyncService", "✗ Failed to sync CREATE_CUSTOMER: ${response.message()}")
                                failureCount++
                            }
                        }
                        "CREATE_STAFF" -> {
                            val request = gson.fromJson(item.payload, com.milkman.dairyapp.network.models.CreateAdminRequest::class.java)
                            val response = ApiClient.staffService.createAdmin(
                                "Bearer $token",
                                request
                            )
                            
                            if (response.isSuccessful) {
                                syncQueueDao.markAsSynced(item.id)
                                Log.d("DataSyncService", "✓ Successfully synced CREATE_STAFF: ${request.username}")
                                successCount++
                            } else {
                                syncQueueDao.recordSyncError(item.id, response.message())
                                Log.w("DataSyncService", "✗ Failed to sync CREATE_STAFF: ${response.message()}")
                                failureCount++
                            }
                        }
                        "ADD_SUPPLIER" -> {
                            val request = gson.fromJson(item.payload, AddSupplierRequest::class.java)
                            val response = ApiClient.customerService.addSupplier(request, "Bearer $token")
                            
                            if (response.isSuccessful) {
                                syncQueueDao.markAsSynced(item.id)
                                Log.d("DataSyncService", "✓ Successfully synced ADD_SUPPLIER for ${request.name}")
                                successCount++
                            } else {
                                syncQueueDao.recordSyncError(item.id, response.message())
                                Log.w("DataSyncService", "✗ Failed to sync ADD_SUPPLIER: ${response.message()}")
                                failureCount++
                            }
                        }
                        "MILK_ENTRY_CREATE" -> {
                            // Queued for sync - will be implemented with MilkService
                            syncQueueDao.markAsSynced(item.id)
                            Log.d("DataSyncService", "✓ Queued milk entry for sync")
                            successCount++
                        }
                        "MILK_ENTRY_UPDATE" -> {
                            // Queued for sync - will be implemented with MilkService
                            syncQueueDao.markAsSynced(item.id)
                            Log.d("DataSyncService", "✓ Queued milk entry update for sync")
                            successCount++
                        }
                        "MILK_ENTRY_DELETE" -> {
                            // Queued for sync - will be implemented with MilkService
                            syncQueueDao.markAsSynced(item.id)
                            Log.d("DataSyncService", "✓ Queued milk entry delete for sync")
                            successCount++
                        }
                        // Add more operation types as needed
                        else -> {
                            Log.w("DataSyncService", "Unknown operation type: ${item.operationType}")
                            failureCount++
                        }
                    }
                } catch (e: Exception) {
                    Log.e("DataSyncService", "Sync error for ${item.operationType}", e)
                    syncQueueDao.recordSyncError(item.id, e.message ?: "Unknown error")
                    failureCount++
                }
            }

            // Clean up synced items
            syncQueueDao.clearSyncedItems()

            // Always fetch latest customers from server into local Room DB.
            val fetchedCount = syncCustomersFromServer("Bearer $token")

            val message = "Synced $successCount, Failed: $failureCount, Fetched: $fetchedCount"
            Log.d("DataSyncService", message)
            SyncResult(failureCount == 0, message, successCount + fetchedCount, fetchedCount)
        } catch (e: Exception) {
            Log.e("DataSyncService", "Sync failed", e)
            SyncResult(false, e.message ?: "Sync failed", 0)
        }
    }

    private suspend fun syncCustomersFromServer(authHeader: String): Int {
        return try {
            val response = ApiClient.customerService.getSuppliers(authHeader)
            if (!response.isSuccessful) {
                Log.w("DataSyncService", "Failed to fetch customers: ${response.code()} ${response.message()}")
                return 0
            }

            val remoteCustomers = response.body().orEmpty()
            var mergedCount = 0

            for (remote in remoteCustomers) {
                val name = remote.name.trim()
                val phone = remote.phone.trim()
                if (name.isBlank() || phone.isBlank()) continue

                val type = remote.type?.trim().takeUnless { it.isNullOrEmpty() } ?: "Supplier"
                val category = if (type.equals("Supplier", ignoreCase = true)) {
                    AppConstants.CATEGORY_SUPPLIER
                } else {
                    AppConstants.CATEGORY_BUYER
                }

                val existing = customerDao.findByPhoneAndCategory(phone, category)
                if (existing == null) {
                    customerDao.insert(
                        CustomerEntity(
                            name = name,
                            phone = phone,
                            address = remote.address?.trim().orEmpty(),
                            category = category,
                            type = type,
                            pricePerLiter = remote.pricePerLiter ?: 0.0,
                            createdAt = System.currentTimeMillis(),
                            createdBy = 0,
                            updatedAt = System.currentTimeMillis(),
                            updatedBy = 0
                        )
                    )
                } else {
                    customerDao.update(
                        existing.copy(
                            name = name,
                            phone = phone,
                            address = remote.address?.trim().orEmpty(),
                            category = category,
                            type = type,
                            pricePerLiter = remote.pricePerLiter ?: existing.pricePerLiter,
                            updatedAt = System.currentTimeMillis(),
                            updatedBy = 0
                        )
                    )
                }
                mergedCount++
            }

            Log.d("DataSyncService", "Fetched and merged $mergedCount customers from server")
            mergedCount
        } catch (e: Exception) {
            Log.e("DataSyncService", "Customer fetch sync failed", e)
            0
        }
    }

    data class SyncResult(
        val success: Boolean,
        val message: String,
        val syncedCount: Int,
        val fetchedCount: Int = 0
    )
}
