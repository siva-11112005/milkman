package com.milkman.dairyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.model.DailySessionBreakdown
import com.milkman.dairyapp.data.repository.ReportRepository
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.TimeUtils

class SessionSummaryViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repository = ReportRepository(db.milkEntryDao())

    private val selectedDate = MutableLiveData(TimeUtils.currentDate())

    val breakdown: LiveData<List<DailySessionBreakdown>> = selectedDate.switchMap { date ->
        repository.dailySessionBreakdown(date, entryType = AppConstants.ENTRY_COLLECTION)
    }

    fun setDate(date: String) {
        selectedDate.value = date
    }
}
