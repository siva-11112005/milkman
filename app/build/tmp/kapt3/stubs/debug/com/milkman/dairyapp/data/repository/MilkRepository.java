package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ>\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001bJS\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001d2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0002\u0010#J\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u001dJ&\u0010%\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010&R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/milkman/dairyapp/data/repository/MilkRepository;", "", "milkEntryDao", "Lcom/milkman/dairyapp/data/dao/MilkEntryDao;", "customerDao", "Lcom/milkman/dairyapp/data/dao/CustomerDao;", "auditRepository", "Lcom/milkman/dairyapp/data/repository/AuditRepository;", "syncQueueDao", "Lcom/milkman/dairyapp/data/dao/SyncQueueDao;", "(Lcom/milkman/dairyapp/data/dao/MilkEntryDao;Lcom/milkman/dairyapp/data/dao/CustomerDao;Lcom/milkman/dairyapp/data/repository/AuditRepository;Lcom/milkman/dairyapp/data/dao/SyncQueueDao;)V", "gson", "Lcom/google/gson/Gson;", "addEntry", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "customerId", "", "entryDate", "", "session", "entryType", "quantityLiters", "", "userId", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;DILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteEntry", "entryId", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEntries", "Landroidx/lifecycle/LiveData;", "", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "date", "customerCategory", "customerType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroidx/lifecycle/LiveData;", "getMilkEntries", "updateEntry", "(IDILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class MilkRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.MilkEntryDao milkEntryDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.CustomerDao customerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.AuditRepository auditRepository = null;
    @org.jetbrains.annotations.Nullable()
    private final com.milkman.dairyapp.data.dao.SyncQueueDao syncQueueDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    public MilkRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.MilkEntryDao milkEntryDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.CustomerDao customerDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.repository.AuditRepository auditRepository, @org.jetbrains.annotations.Nullable()
    com.milkman.dairyapp.data.dao.SyncQueueDao syncQueueDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> getEntries(@org.jetbrains.annotations.Nullable()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String customerType, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> getMilkEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addEntry(int customerId, @org.jetbrains.annotations.NotNull()
    java.lang.String entryDate, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String entryType, double quantityLiters, int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateEntry(int entryId, double quantityLiters, int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteEntry(int entryId, int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
}