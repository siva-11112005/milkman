package com.milkman.dairyapp.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [Index(value = ["name"]), Index(value = ["phone"])]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val address: String,
    val category: String, // SUPPLIER, BUYER
    val type: String,
    val pricePerLiter: Double,
    val createdAt: Long,
    val createdBy: Int,
    val updatedAt: Long? = null,
    val updatedBy: Int? = null
)
