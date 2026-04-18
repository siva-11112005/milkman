package com.milkman.dairyapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.milkman.dairyapp.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun findById(userId: Int): User?

    @Query("SELECT * FROM users WHERE linkedCustomerId = :customerId LIMIT 1")
    suspend fun findByLinkedCustomerId(customerId: Int): User?

    @Query(
        "SELECT * FROM users WHERE username = :username AND passwordHash = :passwordHash AND isActive = 1 LIMIT 1"
    )
    suspend fun login(username: String, passwordHash: String): User?

    @Query("SELECT * FROM users WHERE role = :role ORDER BY fullName ASC")
    fun getUsersByRole(role: String): LiveData<List<User>>

    @Query(
        """
        UPDATE users
        SET fullName = :fullName,
            phone = :phone,
            address = :address
        WHERE id = :userId
        """
    )
    suspend fun updateBasicProfile(userId: Int, fullName: String, phone: String, address: String): Int

    @Query(
        """
        UPDATE users
        SET fullName = :fullName,
            phone = :phone,
            address = :address,
            pricePerLiter = :pricePerLiter
        WHERE id = :userId
        """
    )
    suspend fun updateFullProfile(
        userId: Int,
        fullName: String,
        phone: String,
        address: String,
        pricePerLiter: Double?
    ): Int

    @Query(
        """
        UPDATE users
        SET fullName = :fullName,
            phone = :phone,
            address = :address,
            pricePerLiter = :pricePerLiter
        WHERE linkedCustomerId = :customerId
        """
    )
    suspend fun updateLinkedSupplierProfile(
        customerId: Int,
        fullName: String,
        phone: String,
        address: String,
        pricePerLiter: Double
    ): Int

    @Query("UPDATE users SET passwordHash = :passwordHash WHERE id = :userId")
    suspend fun updatePassword(userId: Int, passwordHash: String): Int

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Int): Int
}
