package com.milkman.dairyapp.data.repository

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.milkman.dairyapp.data.dao.CustomerDao
import com.milkman.dairyapp.data.dao.MilkEntryDao
import com.milkman.dairyapp.data.dao.SyncQueueDao
import com.milkman.dairyapp.data.entity.MilkEntryEntity
import com.milkman.dairyapp.data.entity.SyncQueueEntity
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.data.model.RepositoryResult
import com.milkman.dairyapp.util.TimeUtils

class MilkRepository(
    private val milkEntryDao: MilkEntryDao,
    private val customerDao: CustomerDao,
    private val auditRepository: AuditRepository,
    private val syncQueueDao: SyncQueueDao? = null
) {
    private val gson = Gson()

    fun getEntries(
        date: String? = null,
        customerId: Int? = null,
        customerCategory: String? = null,
        customerType: String? = null,
        entryType: String? = null
    ): LiveData<List<MilkEntryWithCustomer>> {
        return milkEntryDao.getEntries(date, customerId, customerCategory, customerType, entryType)
    }

    fun getMilkEntries(): LiveData<List<MilkEntryWithCustomer>> = milkEntryDao.getMilkEntries()

    suspend fun addEntry(
        customerId: Int,
        entryDate: String,
        session: String,
        entryType: String,
        quantityLiters: Double,
        userId: Int
    ): RepositoryResult {
        val customer = customerDao.findById(customerId) ?: return RepositoryResult(false, "Profile not found")
        val pricePerLiter = customer.pricePerLiter
        if (pricePerLiter <= 0) {
            return RepositoryResult(false, "Please update price in profile before entry")
        }

        val now = TimeUtils.currentTimestamp()
        val amount = quantityLiters * pricePerLiter
        return try {
            val id = milkEntryDao.insert(
                MilkEntryEntity(
                    customerId = customerId,
                    entryDate = entryDate,
                    session = session,
                    entryType = entryType,
                    quantityLiters = quantityLiters,
                    pricePerLiter = pricePerLiter,
                    amount = amount,
                    createdAt = now,
                    createdBy = userId
                )
            )
            auditRepository.log(
                tableName = "milk_entries",
                recordId = id.toInt(),
                action = "CREATE",
                userId = userId,
                details = "Entry added for customer $customerId on $entryDate $session ($entryType)"
            )

            // Queue for sync
            syncQueueDao?.insert(
                SyncQueueEntity(
                    operationType = "MILK_ENTRY_CREATE",
                    tableName = "milk_entries",
                    recordId = id.toInt(),
                    payload = gson.toJson(mapOf(
                        "customerId" to customerId,
                        "entryDate" to entryDate,
                        "session" to session,
                        "entryType" to entryType,
                        "quantityLiters" to quantityLiters,
                        "pricePerLiter" to pricePerLiter
                    ))
                )
            )

            RepositoryResult(true, "📝 Entry saved locally. Will sync to MongoDB Atlas in next sync cycle 🔄", id)
        } catch (e: Exception) {
            val message = if ((e.message ?: "").contains("UNIQUE", true)) {
                "Entry already exists for this profile, date, session and type"
            } else {
                e.message ?: "Unable to save milk entry"
            }
            RepositoryResult(false, message)
        }
    }

    suspend fun updateEntry(entryId: Int, quantityLiters: Double, userId: Int): RepositoryResult {
        val existing = milkEntryDao.findById(entryId) ?: return RepositoryResult(false, "Entry not found")
        if (TimeUtils.isLocked(existing.createdAt)) {
            return RepositoryResult(false, "Record locked after 1 hour; cannot edit")
        }

        val updated = existing.copy(
            quantityLiters = quantityLiters,
            amount = quantityLiters * existing.pricePerLiter,
            updatedAt = TimeUtils.currentTimestamp(),
            updatedBy = userId
        )

        return try {
            milkEntryDao.update(updated)
            auditRepository.log(
                tableName = "milk_entries",
                recordId = entryId,
                action = "UPDATE",
                userId = userId,
                details = "Milk entry updated"
            )

            // Queue for sync
            syncQueueDao?.insert(
                SyncQueueEntity(
                    operationType = "MILK_ENTRY_UPDATE",
                    tableName = "milk_entries",
                    recordId = entryId,
                    payload = gson.toJson(mapOf(
                        "quantityLiters" to quantityLiters,
                        "pricePerLiter" to existing.pricePerLiter
                    ))
                )
            )

            RepositoryResult(true, "Entry updated. Syncing...")
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to update entry")
        }
    }

    suspend fun deleteEntry(entryId: Int, userId: Int): RepositoryResult {
        val existing = milkEntryDao.findById(entryId) ?: return RepositoryResult(false, "Entry not found")
        if (TimeUtils.isLocked(existing.createdAt)) {
            return RepositoryResult(false, "Record locked after 1 hour; cannot delete")
        }

        return try {
            milkEntryDao.delete(existing)
            auditRepository.log(
                tableName = "milk_entries",
                recordId = entryId,
                action = "DELETE",
                userId = userId,
                details = "Milk entry deleted"
            )

            // Queue for sync
            syncQueueDao?.insert(
                SyncQueueEntity(
                    operationType = "MILK_ENTRY_DELETE",
                    tableName = "milk_entries",
                    recordId = entryId,
                    payload = "{}"
                )
            )

            RepositoryResult(true, "Entry deleted. Syncing...")
        } catch (e: Exception) {
            RepositoryResult(false, e.message ?: "Unable to delete entry")
        }
    }
}
