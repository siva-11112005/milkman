package com.milkman.dairyapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.milkman.dairyapp.data.entity.CustomerEntity

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(customer: CustomerEntity): Long

    @Update
    suspend fun update(customer: CustomerEntity): Int

    @Delete
    suspend fun delete(customer: CustomerEntity): Int

    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): LiveData<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE category = :category ORDER BY name ASC")
    fun getCustomersByCategory(category: String): LiveData<List<CustomerEntity>>

    @Query(
        "SELECT * FROM customers WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%' ORDER BY name ASC"
    )
    fun searchCustomers(query: String): LiveData<List<CustomerEntity>>

    @Query(
        """
        SELECT * FROM customers
        WHERE category = :category
          AND (name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%')
        ORDER BY name ASC
        """
    )
    fun searchCustomersByCategory(category: String, query: String): LiveData<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): CustomerEntity?

    @Query("SELECT * FROM customers WHERE phone = :phone AND category = :category LIMIT 1")
    suspend fun findByPhoneAndCategory(phone: String, category: String): CustomerEntity?
}
