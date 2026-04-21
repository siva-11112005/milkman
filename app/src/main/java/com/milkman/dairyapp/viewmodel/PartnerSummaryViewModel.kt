package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.MilkRepository

data class PartnerMonthlySummary(
    val totalQuantity: Double = 0.0,
    val totalAmount: Double = 0.0
)

class PartnerSummaryViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = MilkRepository(
        milkEntryDao = db.milkEntryDao(),
        customerDao = db.customerDao(),
        auditRepository = AuditRepository(db.auditLogDao())
    )

    fun getEntriesForDate(
        date: String,
        customerId: Int,
        entryType: String
    ): LiveData<List<MilkEntryWithCustomer>> {
        return repository.getEntries(
            date = date,
            customerId = customerId,
            entryType = entryType
        )
    }

    fun getMonthlySummary(
        month: String,
        customerId: Int,
        entryType: String
    ): LiveData<PartnerMonthlySummary> {
        return repository.getEntries(
            customerId = customerId,
            entryType = entryType
        ).map { rows ->
            val monthRows = rows.filter { it.entryDate.startsWith(month) }
            PartnerMonthlySummary(
                totalQuantity = monthRows.sumOf { it.quantityLiters },
                totalAmount = monthRows.sumOf { it.amount }
            )
        }
    }
}
