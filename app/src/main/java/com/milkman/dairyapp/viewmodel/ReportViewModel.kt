package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.model.MonthlyCustomerReport
import com.milkman.dairyapp.data.repository.ReportRepository
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.TimeUtils

class ReportViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = ReportRepository(db.milkEntryDao())

    private val selectedMonth = MutableLiveData(TimeUtils.currentMonth())

    val monthlyReport: LiveData<List<MonthlyCustomerReport>> = selectedMonth.switchMap { month ->
        repository.monthlyCustomerReport(
            month = month,
            customerCategory = AppConstants.CATEGORY_SUPPLIER,
            entryType = AppConstants.ENTRY_COLLECTION
        )
    }

    fun setMonth(month: String) {
        selectedMonth.value = month
    }
}
