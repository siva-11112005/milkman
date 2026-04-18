package com.milkman.dairyapp.data.model

data class MonthlyCustomerReport(
    val customerId: Int,
    val customerName: String,
    val customerType: String,
    val totalQuantity: Double,
    val totalAmount: Double
)
