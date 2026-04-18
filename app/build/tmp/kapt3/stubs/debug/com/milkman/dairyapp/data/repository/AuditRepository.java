package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nJ6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/milkman/dairyapp/data/repository/AuditRepository;", "", "auditLogDao", "Lcom/milkman/dairyapp/data/dao/AuditLogDao;", "(Lcom/milkman/dairyapp/data/dao/AuditLogDao;)V", "getRecentLogs", "Landroidx/lifecycle/LiveData;", "", "Lcom/milkman/dairyapp/data/entity/AuditLogEntity;", "limit", "", "log", "", "tableName", "", "recordId", "action", "userId", "details", "(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuditRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.AuditLogDao auditLogDao = null;
    
    public AuditRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.AuditLogDao auditLogDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.AuditLogEntity>> getRecentLogs(int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object log(@org.jetbrains.annotations.NotNull()
    java.lang.String tableName, int recordId, @org.jetbrains.annotations.NotNull()
    java.lang.String action, int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String details, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}