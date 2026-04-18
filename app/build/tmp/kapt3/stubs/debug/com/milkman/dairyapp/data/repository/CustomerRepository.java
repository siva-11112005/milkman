package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ>\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018JF\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u001dJ&\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\"J\u0018\u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010$\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010%J \u0010&\u001a\u0004\u0018\u00010\'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\'0)2\u0006\u0010*\u001a\u00020 H\u0002J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0)0,J\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0)0,J\u0012\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0)0,J\u001a\u0010/\u001a\u00020\u00102\b\u00100\u001a\u0004\u0018\u00010\u00102\u0006\u00101\u001a\u00020\u0010H\u0002J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0)0,2\u0006\u00103\u001a\u00020\u0010J\u001a\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0)0,2\u0006\u00103\u001a\u00020\u0010JF\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u00107J>\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010:R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/milkman/dairyapp/data/repository/CustomerRepository;", "", "customerDao", "Lcom/milkman/dairyapp/data/dao/CustomerDao;", "userDao", "Lcom/milkman/dairyapp/data/dao/UserDao;", "auditRepository", "Lcom/milkman/dairyapp/data/repository/AuditRepository;", "syncQueueDao", "Lcom/milkman/dairyapp/data/dao/SyncQueueDao;", "(Lcom/milkman/dairyapp/data/dao/CustomerDao;Lcom/milkman/dairyapp/data/dao/UserDao;Lcom/milkman/dairyapp/data/repository/AuditRepository;Lcom/milkman/dairyapp/data/dao/SyncQueueDao;)V", "gson", "Lcom/google/gson/Gson;", "addBuyer", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "name", "", "phone", "address", "type", "sellingPricePerLiter", "", "adminUserId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addSupplier", "buyingPricePerLiter", "username", "password", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomer", "customer", "Lcom/milkman/dairyapp/data/entity/CustomerEntity;", "authToken", "(Lcom/milkman/dairyapp/data/entity/CustomerEntity;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findById", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findMatchingRemoteCustomer", "Lcom/milkman/dairyapp/network/models/CustomerResponse;", "remoteCustomers", "", "localCustomer", "getAllCustomers", "Landroidx/lifecycle/LiveData;", "getBuyers", "getSuppliers", "parseServerMessage", "errorBody", "fallbackMessage", "searchBuyers", "query", "searchSuppliers", "updateBuyer", "buyerId", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSupplier", "supplierId", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;DILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class CustomerRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.CustomerDao customerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.AuditRepository auditRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.SyncQueueDao syncQueueDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public CustomerRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.CustomerDao customerDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.repository.AuditRepository auditRepository, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.SyncQueueDao syncQueueDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getAllCustomers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getSuppliers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> searchSuppliers(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getBuyers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> searchBuyers(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object findById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.entity.CustomerEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSupplier(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, double buyingPricePerLiter, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password, int adminUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addBuyer(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String type, double sellingPricePerLiter, int adminUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateSupplier(int supplierId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, double buyingPricePerLiter, int adminUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBuyer(int buyerId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String type, double sellingPricePerLiter, int adminUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCustomer(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.entity.CustomerEntity customer, int adminUserId, @org.jetbrains.annotations.NotNull()
    java.lang.String authToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    private final com.milkman.dairyapp.network.models.CustomerResponse findMatchingRemoteCustomer(java.util.List<com.milkman.dairyapp.network.models.CustomerResponse> remoteCustomers, com.milkman.dairyapp.data.entity.CustomerEntity localCustomer) {
        return null;
    }
    
    private final java.lang.String parseServerMessage(java.lang.String errorBody, java.lang.String fallbackMessage) {
        return null;
    }
}