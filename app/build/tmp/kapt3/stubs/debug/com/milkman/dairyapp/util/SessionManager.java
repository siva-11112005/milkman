package com.milkman.dairyapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0012\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\tJ\b\u0010\u0012\u001a\u0004\u0018\u00010\tJ\u0006\u0010\u0013\u001a\u00020\fJ\u0006\u0010\u0014\u001a\u00020\fJ\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\fJ0\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\tJ\u0016\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tJ\u0006\u0010\u0018\u001a\u00020\tJ\u0006\u0010\u0019\u001a\u00020\tR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/milkman/dairyapp/util/SessionManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "actingAdminId", "", "actingAdminName", "canManageUsersAndCustomers", "", "clearActingAdmin", "", "clearSession", "fullName", "getRole", "getToken", "isActingAsAdmin", "isAdmin", "isLoggedIn", "isSuperUser", "saveSession", "userId", "username", "role", "token", "setActingAdmin", "adminId", "adminName", "Companion", "app_debug"})
public final class SessionManager {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LOGGED_IN = "logged_in";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USER_ID = "user_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_USERNAME = "username";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ROLE = "role";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FULL_NAME = "full_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TOKEN = "token";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ACTING_ADMIN_ID = "acting_admin_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ACTING_ADMIN_NAME = "acting_admin_name";
    @org.jetbrains.annotations.NotNull()
    public static final com.milkman.dairyapp.util.SessionManager.Companion Companion = null;
    
    public SessionManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void saveSession(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void clearSession() {
    }
    
    public final void setActingAdmin(@org.jetbrains.annotations.NotNull()
    java.lang.String adminId, @org.jetbrains.annotations.NotNull()
    java.lang.String adminName) {
    }
    
    public final void clearActingAdmin() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String actingAdminId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String actingAdminName() {
        return null;
    }
    
    public final boolean isActingAsAdmin() {
        return false;
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String userId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String username() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fullName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRole() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final boolean isAdmin() {
        return false;
    }
    
    public final boolean isSuperUser() {
        return false;
    }
    
    public final boolean canManageUsersAndCustomers() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/milkman/dairyapp/util/SessionManager$Companion;", "", "()V", "KEY_ACTING_ADMIN_ID", "", "KEY_ACTING_ADMIN_NAME", "KEY_FULL_NAME", "KEY_LOGGED_IN", "KEY_ROLE", "KEY_TOKEN", "KEY_USERNAME", "KEY_USER_ID", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}