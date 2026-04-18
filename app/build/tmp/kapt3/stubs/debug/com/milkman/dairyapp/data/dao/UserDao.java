package com.milkman.dairyapp.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\fH\'J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0018J.\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ8\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00a7@\u00a2\u0006\u0002\u0010!J6\u0010\"\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 H\u00a7@\u00a2\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010%\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006&\u00c0\u0006\u0001"}, d2 = {"Lcom/milkman/dairyapp/data/dao/UserDao;", "", "deleteById", "", "userId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findById", "Lcom/milkman/dairyapp/data/model/User;", "findByLinkedCustomerId", "customerId", "getUserByUsername", "username", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsersByRole", "Landroidx/lifecycle/LiveData;", "", "role", "insert", "", "user", "(Lcom/milkman/dairyapp/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "passwordHash", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBasicProfile", "fullName", "phone", "address", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateFullProfile", "pricePerLiter", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLinkedSupplierProfile", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassword", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UserDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE username = :username LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE id = :userId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findById(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE linkedCustomerId = :customerId LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object findByLinkedCustomerId(int customerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE username = :username AND passwordHash = :passwordHash AND isActive = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String passwordHash, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE role = :role ORDER BY fullName ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.User>> getUsersByRole(@org.jetbrains.annotations.NotNull()
    java.lang.String role);
    
    @androidx.room.Query(value = "\n        UPDATE users\n        SET fullName = :fullName,\n            phone = :phone,\n            address = :address\n        WHERE id = :userId\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBasicProfile(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "\n        UPDATE users\n        SET fullName = :fullName,\n            phone = :phone,\n            address = :address,\n            pricePerLiter = :pricePerLiter\n        WHERE id = :userId\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateFullProfile(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.Nullable()
    java.lang.Double pricePerLiter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "\n        UPDATE users\n        SET fullName = :fullName,\n            phone = :phone,\n            address = :address,\n            pricePerLiter = :pricePerLiter\n        WHERE linkedCustomerId = :customerId\n        ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateLinkedSupplierProfile(int customerId, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, double pricePerLiter, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "UPDATE users SET passwordHash = :passwordHash WHERE id = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePassword(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String passwordHash, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM users WHERE id = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}