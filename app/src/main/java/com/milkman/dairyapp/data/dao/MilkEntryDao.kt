package com.milkman.dairyapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.milkman.dairyapp.data.entity.MilkEntryEntity
import com.milkman.dairyapp.data.model.DailySessionBreakdown
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.data.model.MonthlyCustomerReport
import com.milkman.dairyapp.data.model.TypeBreakdown

@Dao
interface MilkEntryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: MilkEntryEntity): Long

    @Update
    suspend fun update(entry: MilkEntryEntity): Int

    @Delete
    suspend fun delete(entry: MilkEntryEntity): Int

    @Query("SELECT * FROM milk_entries WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): MilkEntryEntity?

    @Query(
        """
        SELECT
            m.id,
            m.customerId,
            c.name AS customerName,
            c.category AS customerCategory,
            c.type AS customerType,
            m.entryDate,
            m.session,
            m.entryType,
            m.quantityLiters,
            m.pricePerLiter,
            m.amount,
            m.createdAt,
            m.createdBy,
            m.updatedAt,
            m.updatedBy
        FROM milk_entries m
        INNER JOIN customers c ON c.id = m.customerId
        WHERE (:date IS NULL OR m.entryDate = :date)
          AND (:customerId IS NULL OR m.customerId = :customerId)
          AND (:customerCategory IS NULL OR c.category = :customerCategory)
          AND (:customerType IS NULL OR c.type = :customerType)
          AND (:entryType IS NULL OR m.entryType = :entryType)
        ORDER BY m.entryDate DESC,
                 CASE WHEN m.session = 'MORNING' THEN 0 ELSE 1 END ASC,
                 m.createdAt DESC
        """
    )
    fun getEntries(
        date: String?,
        customerId: Int?,
        customerCategory: String?,
        customerType: String?,
        entryType: String?
    ): LiveData<List<MilkEntryWithCustomer>>

    @Query(
        """
        SELECT
            m.id,
            m.customerId,
            c.name AS customerName,
            c.category AS customerCategory,
            c.type AS customerType,
            m.entryDate,
            m.session,
            m.entryType,
            m.quantityLiters,
            m.pricePerLiter,
            m.amount,
            m.createdAt,
            m.createdBy,
            m.updatedAt,
            m.updatedBy
        FROM milk_entries m
        INNER JOIN customers c ON c.id = m.customerId
        ORDER BY m.entryDate DESC,
                 CASE WHEN m.session = 'MORNING' THEN 0 ELSE 1 END ASC,
                 m.createdAt DESC
        """
    )
    fun getMilkEntries(): LiveData<List<MilkEntryWithCustomer>>

    @Query(
        """
        SELECT IFNULL(SUM(quantityLiters), 0)
        FROM milk_entries
        WHERE entryDate = :date
          AND (:entryType IS NULL OR entryType = :entryType)
          AND (:customerId IS NULL OR customerId = :customerId)
        """
    )
    fun getDailyQuantity(date: String, entryType: String? = null, customerId: Int? = null): LiveData<Double>

    @Query(
        """
        SELECT IFNULL(SUM(amount), 0)
        FROM milk_entries
        WHERE entryDate = :date
          AND (:entryType IS NULL OR entryType = :entryType)
          AND (:customerId IS NULL OR customerId = :customerId)
        """
    )
    fun getDailyAmount(date: String, entryType: String? = null, customerId: Int? = null): LiveData<Double>

    @Query(
        """
        SELECT IFNULL(SUM(quantityLiters), 0)
        FROM milk_entries
        WHERE substr(entryDate, 1, 7) = :month
          AND (:entryType IS NULL OR entryType = :entryType)
          AND (:customerId IS NULL OR customerId = :customerId)
        """
    )
    fun getMonthlyQuantity(month: String, entryType: String? = null, customerId: Int? = null): LiveData<Double>

    @Query(
        """
        SELECT IFNULL(SUM(amount), 0)
        FROM milk_entries
        WHERE substr(entryDate, 1, 7) = :month
          AND (:entryType IS NULL OR entryType = :entryType)
          AND (:customerId IS NULL OR customerId = :customerId)
        """
    )
    fun getMonthlyAmount(month: String, entryType: String? = null, customerId: Int? = null): LiveData<Double>

    @Query(
        """
        SELECT
            c.type AS customerType,
            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,
            IFNULL(SUM(m.amount), 0) AS totalAmount
        FROM milk_entries m
        INNER JOIN customers c ON c.id = m.customerId
        WHERE substr(m.entryDate, 1, 7) = :month
          AND (:customerCategory IS NULL OR c.category = :customerCategory)
          AND (:entryType IS NULL OR m.entryType = :entryType)
        GROUP BY c.type
        ORDER BY c.type ASC
        """
    )
    fun getTypeBreakdown(
        month: String,
        customerCategory: String? = null,
        entryType: String? = null
    ): LiveData<List<TypeBreakdown>>

    @Query(
        """
        SELECT
            c.id AS customerId,
            c.name AS customerName,
            c.type AS customerType,
            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,
            IFNULL(SUM(m.amount), 0) AS totalAmount
        FROM customers c
        LEFT JOIN milk_entries m
            ON m.customerId = c.id
           AND substr(m.entryDate, 1, 7) = :month
           AND (:entryType IS NULL OR m.entryType = :entryType)
        WHERE (:customerCategory IS NULL OR c.category = :customerCategory)
        GROUP BY c.id, c.name, c.type
        ORDER BY c.name ASC
        """
    )
    fun getMonthlyCustomerReport(
        month: String,
        customerCategory: String? = null,
        entryType: String? = null
    ): LiveData<List<MonthlyCustomerReport>>

    @Query(
        """
        SELECT
            session,
            IFNULL(SUM(quantityLiters), 0) AS totalQuantity,
            IFNULL(SUM(amount), 0) AS totalAmount,
            COUNT(*) AS entryCount
        FROM milk_entries
        WHERE entryDate = :date
          AND (:entryType IS NULL OR entryType = :entryType)
          AND (:customerId IS NULL OR customerId = :customerId)
        GROUP BY session
        ORDER BY CASE WHEN session = 'MORNING' THEN 0 ELSE 1 END
        """
    )
    fun getDailySessionBreakdown(
        date: String,
        entryType: String? = null,
        customerId: Int? = null
    ): LiveData<List<DailySessionBreakdown>>
}
