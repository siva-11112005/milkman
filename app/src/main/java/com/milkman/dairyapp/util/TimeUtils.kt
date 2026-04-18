package com.milkman.dairyapp.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object TimeUtils {
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val monthFormatter = SimpleDateFormat("yyyy-MM", Locale.getDefault())
    private val dateTimeFormatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())

    fun currentDate(): String = dateFormatter.format(Date())

    fun currentMonth(): String = monthFormatter.format(Date())

    fun formatDate(millis: Long): String = dateFormatter.format(Date(millis))

    fun formatDateTime(millis: Long): String = dateTimeFormatter.format(Date(millis))

    fun monthFromDate(date: String): String = date.take(7)

    fun currentTimestamp(): Long = System.currentTimeMillis()

    fun isLocked(createdAt: Long, userRole: String = AppConstants.ROLE_ADMIN): Boolean {
        // Super users can edit anytime (no lock)
        if (userRole == AppConstants.ROLE_SUPER_USER) {
            return false
        }
        // Other users follow time-based restrictions
        return System.currentTimeMillis() - createdAt > AppConstants.LOCK_WINDOW_MS
    }

    fun toDisplayDate(date: String): String {
        return try {
            val parsed = dateFormatter.parse(date) ?: return date
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(parsed)
        } catch (_: Exception) {
            date
        }
    }

    fun dateFromPicker(year: Int, monthZeroBased: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthZeroBased, day, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return dateFormatter.format(calendar.time)
    }
}
