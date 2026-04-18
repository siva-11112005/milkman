package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ.\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0015JH\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/milkman/dairyapp/data/repository/ProfileRepository;", "", "userDao", "Lcom/milkman/dairyapp/data/dao/UserDao;", "customerDao", "Lcom/milkman/dairyapp/data/dao/CustomerDao;", "auditRepository", "Lcom/milkman/dairyapp/data/repository/AuditRepository;", "(Lcom/milkman/dairyapp/data/dao/UserDao;Lcom/milkman/dairyapp/data/dao/CustomerDao;Lcom/milkman/dairyapp/data/repository/AuditRepository;)V", "changePassword", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "userId", "", "currentPassword", "", "newPassword", "actorUserId", "(ILjava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "Lcom/milkman/dairyapp/data/model/User;", "Lcom/milkman/dairyapp/data/entity/UserEntity;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfile", "fullName", "phone", "address", "pricePerLiter", "", "canEditPrice", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ProfileRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.CustomerDao customerDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.AuditRepository auditRepository = null;
    
    public ProfileRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.CustomerDao customerDao, @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.repository.AuditRepository auditRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(int userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProfile(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.Nullable()
    java.lang.Double pricePerLiter, int actorUserId, boolean canEditPrice, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object changePassword(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String currentPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, int actorUserId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.milkman.dairyapp.data.model.RepositoryResult> $completion) {
        return null;
    }
}