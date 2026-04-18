package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ \u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/milkman/dairyapp/data/repository/AuthRepository;", "", "userDao", "Lcom/milkman/dairyapp/data/dao/UserDao;", "auditRepository", "Lcom/milkman/dairyapp/data/repository/AuditRepository;", "(Lcom/milkman/dairyapp/data/dao/UserDao;Lcom/milkman/dairyapp/data/repository/AuditRepository;)V", "ensureDefaultAdmin", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lcom/milkman/dairyapp/data/model/User;", "username", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.AuditRepository auditRepository = null;
    
    public AuthRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.repository.AuditRepository auditRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureDefaultAdmin(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion) {
        return null;
    }
}