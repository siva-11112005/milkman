package com.milkman.dairyapp.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001f"}, d2 = {"Lcom/milkman/dairyapp/network/ApiClient;", "", "()V", "BASE_URL", "", "adminScopeId", "authService", "Lcom/milkman/dairyapp/network/AuthService;", "getAuthService", "()Lcom/milkman/dairyapp/network/AuthService;", "customerService", "Lcom/milkman/dairyapp/network/CustomerService;", "getCustomerService", "()Lcom/milkman/dairyapp/network/CustomerService;", "httpClient", "Lokhttp3/OkHttpClient;", "milkEntryService", "Lcom/milkman/dairyapp/network/MilkEntryService;", "getMilkEntryService", "()Lcom/milkman/dairyapp/network/MilkEntryService;", "retrofit", "Lretrofit2/Retrofit;", "kotlin.jvm.PlatformType", "staffService", "Lcom/milkman/dairyapp/network/StaffService;", "getStaffService", "()Lcom/milkman/dairyapp/network/StaffService;", "clearAdminScope", "", "setAdminScope", "adminId", "app_debug"})
public final class ApiClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://milkman-wjb4.onrender.com/api/";
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile java.lang.String adminScopeId;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient httpClient = null;
    private static final retrofit2.Retrofit retrofit = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.milkman.dairyapp.network.AuthService authService = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.milkman.dairyapp.network.CustomerService customerService = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.milkman.dairyapp.network.StaffService staffService = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.milkman.dairyapp.network.MilkEntryService milkEntryService = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.milkman.dairyapp.network.ApiClient INSTANCE = null;
    
    private ApiClient() {
        super();
    }
    
    public final void setAdminScope(@org.jetbrains.annotations.Nullable()
    java.lang.String adminId) {
    }
    
    public final void clearAdminScope() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.milkman.dairyapp.network.AuthService getAuthService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.milkman.dairyapp.network.CustomerService getCustomerService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.milkman.dairyapp.network.StaffService getStaffService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.milkman.dairyapp.network.MilkEntryService getMilkEntryService() {
        return null;
    }
}