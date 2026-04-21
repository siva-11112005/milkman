package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.data.repository.AuditRepository
import com.milkman.dairyapp.data.repository.MilkRepository
import com.milkman.dairyapp.util.AppConstants

data class AdminDailySummary(
    val milkCollected: Double = 0.0,
    val milkSold: Double = 0.0,
    val milkToTeaShops: Double = 0.0,
    val milkToIndustries: Double = 0.0,
    val buyingAmount: Double = 0.0,
    val sellingAmount: Double = 0.0,
    val profit: Double = 0.0
)

data class CustomerDailySummary(
    val totalQuantity: Double = 0.0,
    val morningQuantity: Double = 0.0,
    val eveningQuantity: Double = 0.0,
    val earnings: Double = 0.0
)

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val milkRepository = MilkRepository(
        milkEntryDao = db.milkEntryDao(),
        customerDao = db.customerDao(),
        auditRepository = AuditRepository(db.auditLogDao())
    )

    val suppliers: LiveData<List<CustomerEntity>> = db.customerDao().getCustomersByCategory(AppConstants.CATEGORY_SUPPLIER)
    val buyers: LiveData<List<CustomerEntity>> = db.customerDao().getCustomersByCategory(AppConstants.CATEGORY_BUYER)

    fun getAdminDailySummary(date: String): LiveData<AdminDailySummary> {
        return milkRepository.getEntries(date = date).map { rows ->
            rows.toAdminSummary()
        }
    }

    fun getCustomerDailySummary(date: String, supplierId: Int): LiveData<CustomerDailySummary> {
        return milkRepository.getEntries(
            date = date,
            customerId = supplierId,
            entryType = AppConstants.ENTRY_COLLECTION
        ).map { rows ->
            val morningQty = rows
                .filter { it.session == "MORNING" }
                .sumOf { it.quantityLiters }
            val eveningQty = rows
                .filter { it.session == "EVENING" }
                .sumOf { it.quantityLiters }
            val totalQty = rows.sumOf { it.quantityLiters }
            val earnings = rows.sumOf { it.amount }

            CustomerDailySummary(
                totalQuantity = totalQty,
                morningQuantity = morningQty,
                eveningQuantity = eveningQty,
                earnings = earnings
            )
        }
    }

    fun getPartnerDailyAmount(date: String, customerId: Int, entryType: String): LiveData<Double> {
        return milkRepository.getEntries(
            date = date,
            customerId = customerId,
            entryType = entryType
        ).map { rows ->
            rows.sumOf { it.amount }
        }
    }

    private fun List<MilkEntryWithCustomer>.toAdminSummary(): AdminDailySummary {
        val collections = filter {
            it.entryType == AppConstants.ENTRY_COLLECTION &&
                it.customerCategory == AppConstants.CATEGORY_SUPPLIER
        }
        val distributions = filter {
            it.entryType == AppConstants.ENTRY_DISTRIBUTION &&
                it.customerCategory == AppConstants.CATEGORY_BUYER
        }
        val teaShops = distributions
            .filter { it.customerType == AppConstants.BUYER_TYPE_TEA_SHOP }
            .sumOf { it.quantityLiters }
        val industries = distributions
            .filter { it.customerType == AppConstants.BUYER_TYPE_INDUSTRY }
            .sumOf { it.quantityLiters }

        val buying = collections.sumOf { it.amount }
        val selling = distributions.sumOf { it.amount }

        return AdminDailySummary(
            milkCollected = collections.sumOf { it.quantityLiters },
            milkSold = distributions.sumOf { it.quantityLiters },
            milkToTeaShops = teaShops,
            milkToIndustries = industries,
            buyingAmount = buying,
            sellingAmount = selling,
            profit = selling - buying
        )
    }
}
