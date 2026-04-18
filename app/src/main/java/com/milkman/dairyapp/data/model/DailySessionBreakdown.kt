package com.milkman.dairyapp.data.model

data class DailySessionBreakdown(
    val session: String,
    val totalQuantity: Double,
    val totalAmount: Double,
    val entryCount: Int
)
