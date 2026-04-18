package com.milkman.dairyapp.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "milk_entries",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["customerId"]),
        Index(value = ["entryDate"]),
        Index(value = ["customerId", "entryDate", "session", "entryType"], unique = true)
    ]
)
data class MilkEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerId: Int,
    val entryDate: String,
    val session: String,
    val entryType: String, // COLLECTION, DISTRIBUTION
    val quantityLiters: Double,
    val pricePerLiter: Double,
    val amount: Double,
    val createdAt: Long,
    val createdBy: Int,
    val updatedAt: Long? = null,
    val updatedBy: Int? = null
)
