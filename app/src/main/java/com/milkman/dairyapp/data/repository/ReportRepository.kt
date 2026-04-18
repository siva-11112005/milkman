package com.milkman.dairyapp.data.repository

import androidx.lifecycle.LiveData
import com.milkman.dairyapp.data.dao.MilkEntryDao
import com.milkman.dairyapp.data.model.DailySessionBreakdown
import com.milkman.dairyapp.data.model.MonthlyCustomerReport
import com.milkman.dairyapp.data.model.TypeBreakdown

class ReportRepository(private val milkEntryDao: MilkEntryDao) {
    fun dailyQuantity(
        date: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<Double> = milkEntryDao.getDailyQuantity(date, entryType, customerId)

    fun dailyAmount(
        date: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<Double> = milkEntryDao.getDailyAmount(date, entryType, customerId)

    fun monthlyQuantity(
        month: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<Double> = milkEntryDao.getMonthlyQuantity(month, entryType, customerId)

    fun monthlyAmount(
        month: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<Double> = milkEntryDao.getMonthlyAmount(month, entryType, customerId)

    fun typeBreakdown(
        month: String,
        customerCategory: String? = null,
        entryType: String? = null
    ): LiveData<List<TypeBreakdown>> = milkEntryDao.getTypeBreakdown(month, customerCategory, entryType)

    fun monthlyCustomerReport(
        month: String,
        customerCategory: String? = null,
        entryType: String? = null
    ): LiveData<List<MonthlyCustomerReport>> =
        milkEntryDao.getMonthlyCustomerReport(month, customerCategory, entryType)

    fun dailySessionBreakdown(
        date: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<List<DailySessionBreakdown>> =
        milkEntryDao.getDailySessionBreakdown(date, entryType, customerId)
}
