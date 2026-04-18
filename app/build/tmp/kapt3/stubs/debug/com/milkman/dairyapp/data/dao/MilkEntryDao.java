package com.milkman.dairyapp.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\tJ3\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\'\u00a2\u0006\u0002\u0010\u0011J3\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\'\u00a2\u0006\u0002\u0010\u0011J9\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\'\u00a2\u0006\u0002\u0010\u0011JK\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\'\u00a2\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u000bH\'J3\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001d\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\'\u00a2\u0006\u0002\u0010\u0011J4\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00140\u000b2\u0006\u0010\u001d\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\'J3\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001d\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\'\u00a2\u0006\u0002\u0010\u0011J4\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00140\u000b2\u0006\u0010\u001d\u001a\u00020\u000e2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\'J\u0016\u0010#\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006&\u00c0\u0006\u0001"}, d2 = {"Lcom/milkman/dairyapp/data/dao/MilkEntryDao;", "", "delete", "", "entry", "Lcom/milkman/dairyapp/data/entity/MilkEntryEntity;", "(Lcom/milkman/dairyapp/data/entity/MilkEntryEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDailyAmount", "Landroidx/lifecycle/LiveData;", "", "date", "", "entryType", "customerId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "getDailyQuantity", "getDailySessionBreakdown", "", "Lcom/milkman/dairyapp/data/model/DailySessionBreakdown;", "getEntries", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "customerCategory", "customerType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroidx/lifecycle/LiveData;", "getMilkEntries", "getMonthlyAmount", "month", "getMonthlyCustomerReport", "Lcom/milkman/dairyapp/data/model/MonthlyCustomerReport;", "getMonthlyQuantity", "getTypeBreakdown", "Lcom/milkman/dairyapp/data/model/TypeBreakdown;", "insert", "", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface MilkEntryDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.entity.MilkEntryEntity entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.entity.MilkEntryEntity entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.entity.MilkEntryEntity entry, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM milk_entries WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.entity.MilkEntryEntity> $completion);
    
    @androidx.room.Query(value = "\n        SELECT\n            m.id,\n            m.customerId,\n            c.name AS customerName,\n            c.category AS customerCategory,\n            c.type AS customerType,\n            m.entryDate,\n            m.session,\n            m.entryType,\n            m.quantityLiters,\n            m.pricePerLiter,\n            m.amount,\n            m.createdAt,\n            m.createdBy,\n            m.updatedAt,\n            m.updatedBy\n        FROM milk_entries m\n        INNER JOIN customers c ON c.id = m.customerId\n        WHERE (:date IS NULL OR m.entryDate = :date)\n          AND (:customerId IS NULL OR m.customerId = :customerId)\n          AND (:customerCategory IS NULL OR c.category = :customerCategory)\n          AND (:customerType IS NULL OR c.type = :customerType)\n          AND (:entryType IS NULL OR m.entryType = :entryType)\n        ORDER BY m.entryDate DESC,\n                 CASE WHEN m.session = \'MORNING\' THEN 0 ELSE 1 END ASC,\n                 m.createdAt DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> getEntries(@org.jetbrains.annotations.Nullable()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String customerType, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType);
    
    @androidx.room.Query(value = "\n        SELECT\n            m.id,\n            m.customerId,\n            c.name AS customerName,\n            c.category AS customerCategory,\n            c.type AS customerType,\n            m.entryDate,\n            m.session,\n            m.entryType,\n            m.quantityLiters,\n            m.pricePerLiter,\n            m.amount,\n            m.createdAt,\n            m.createdBy,\n            m.updatedAt,\n            m.updatedBy\n        FROM milk_entries m\n        INNER JOIN customers c ON c.id = m.customerId\n        ORDER BY m.entryDate DESC,\n                 CASE WHEN m.session = \'MORNING\' THEN 0 ELSE 1 END ASC,\n                 m.createdAt DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> getMilkEntries();
    
    @androidx.room.Query(value = "\n        SELECT IFNULL(SUM(quantityLiters), 0)\n        FROM milk_entries\n        WHERE entryDate = :date\n          AND (:entryType IS NULL OR entryType = :entryType)\n          AND (:customerId IS NULL OR customerId = :customerId)\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getDailyQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId);
    
    @androidx.room.Query(value = "\n        SELECT IFNULL(SUM(amount), 0)\n        FROM milk_entries\n        WHERE entryDate = :date\n          AND (:entryType IS NULL OR entryType = :entryType)\n          AND (:customerId IS NULL OR customerId = :customerId)\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getDailyAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId);
    
    @androidx.room.Query(value = "\n        SELECT IFNULL(SUM(quantityLiters), 0)\n        FROM milk_entries\n        WHERE substr(entryDate, 1, 7) = :month\n          AND (:entryType IS NULL OR entryType = :entryType)\n          AND (:customerId IS NULL OR customerId = :customerId)\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getMonthlyQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId);
    
    @androidx.room.Query(value = "\n        SELECT IFNULL(SUM(amount), 0)\n        FROM milk_entries\n        WHERE substr(entryDate, 1, 7) = :month\n          AND (:entryType IS NULL OR entryType = :entryType)\n          AND (:customerId IS NULL OR customerId = :customerId)\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getMonthlyAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId);
    
    @androidx.room.Query(value = "\n        SELECT\n            c.type AS customerType,\n            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,\n            IFNULL(SUM(m.amount), 0) AS totalAmount\n        FROM milk_entries m\n        INNER JOIN customers c ON c.id = m.customerId\n        WHERE substr(m.entryDate, 1, 7) = :month\n          AND (:customerCategory IS NULL OR c.category = :customerCategory)\n          AND (:entryType IS NULL OR m.entryType = :entryType)\n        GROUP BY c.type\n        ORDER BY c.type ASC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.TypeBreakdown>> getTypeBreakdown(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType);
    
    @androidx.room.Query(value = "\n        SELECT\n            c.id AS customerId,\n            c.name AS customerName,\n            c.type AS customerType,\n            IFNULL(SUM(m.quantityLiters), 0) AS totalQuantity,\n            IFNULL(SUM(m.amount), 0) AS totalAmount\n        FROM customers c\n        LEFT JOIN milk_entries m\n            ON m.customerId = c.id\n           AND substr(m.entryDate, 1, 7) = :month\n           AND (:entryType IS NULL OR m.entryType = :entryType)\n        WHERE (:customerCategory IS NULL OR c.category = :customerCategory)\n        GROUP BY c.id, c.name, c.type\n        ORDER BY c.name ASC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MonthlyCustomerReport>> getMonthlyCustomerReport(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType);
    
    @androidx.room.Query(value = "\n        SELECT\n            session,\n            IFNULL(SUM(quantityLiters), 0) AS totalQuantity,\n            IFNULL(SUM(amount), 0) AS totalAmount,\n            COUNT(*) AS entryCount\n        FROM milk_entries\n        WHERE entryDate = :date\n          AND (:entryType IS NULL OR entryType = :entryType)\n          AND (:customerId IS NULL OR customerId = :customerId)\n        GROUP BY session\n        ORDER BY CASE WHEN session = \'MORNING\' THEN 0 ELSE 1 END\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.DailySessionBreakdown>> getDailySessionBreakdown(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId);
}