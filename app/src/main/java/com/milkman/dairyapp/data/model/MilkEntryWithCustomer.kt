package com.milkman.dairyapp.data.model

data class MilkEntryWithCustomer(
    val id: Int,
    val customerId: Int,
    val customerName: String,
    val customerCategory: String,
    val customerType: String,
    val entryDate: String,
    val session: String,
    val entryType: String,
    val quantityLiters: Double,
    val pricePerLiter: Double,
    val amount: Double,
    val createdAt: Long,
    val createdBy: Int,
    val updatedAt: Long?,
    val updatedBy: Int?
)
