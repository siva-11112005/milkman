package com.milkman.dairyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val passwordHash: String,
    val role: String, // ADMIN, CUSTOMER
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val createdBy: Int? = null,
    val phone: String? = null,
    val address: String? = null,
    val pricePerLiter: Double? = null, // For customers (suppliers)
    val customerType: String? = null,
    val linkedCustomerId: Int? = null
)
