package com.milkman.dairyapp.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\n \u0015*\u0004\u0018\u00010\u00140\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/milkman/dairyapp/network/ApiClient;", "", "()V", "BASE_URL", "", "authService", "Lcom/milkman/dairyapp/network/AuthService;", "getAuthService", "()Lcom/milkman/dairyapp/network/AuthService;", "customerService", "Lcom/milkman/dairyapp/network/CustomerService;", "getCustomerService", "()Lcom/milkman/dairyapp/network/CustomerService;", "httpClient", "Lokhttp3/OkHttpClient;", "milkEntryService", "Lcom/milkman/dairyapp/network/MilkEntryService;", "getMilkEntryService", "()Lcom/milkman/dairyapp/network/MilkEntryService;", "retrofit", "Lretrofit2/Retrofit;", "kotlin.jvm.PlatformType", "staffService", "Lcom/milkman/dairyapp/network/StaffService;", "getStaffService", "()Lcom/milkman/dairyapp/network/StaffService;", "app_debug"})
public final class ApiClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "http://10.0.2.2:8080/api/";
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