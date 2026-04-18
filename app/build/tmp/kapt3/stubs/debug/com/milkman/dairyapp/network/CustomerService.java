package com.milkman.dairyapp.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u000b2\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\fJ4\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0014J>\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000f\u001a\u00020\b2\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0017\u00c0\u0006\u0001"}, d2 = {"Lcom/milkman/dairyapp/network/CustomerService;", "", "addSupplier", "Lretrofit2/Response;", "Lcom/milkman/dairyapp/network/models/AddSupplierResponse;", "request", "Lcom/milkman/dairyapp/network/models/AddSupplierRequest;", "token", "", "(Lcom/milkman/dairyapp/network/models/AddSupplierRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCustomer", "Lcom/milkman/dairyapp/network/models/CreateCustomerRequest;", "(Lcom/milkman/dairyapp/network/models/CreateCustomerRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomer", "", "customerId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSuppliers", "", "Lcom/milkman/dairyapp/network/models/CustomerResponse;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCustomer", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface CustomerService {
    
    @retrofit2.http.GET(value = "customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSuppliers(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.milkman.dairyapp.network.models.CustomerResponse>>> $completion);
    
    @retrofit2.http.POST(value = "customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createCustomer(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.network.models.CreateCustomerRequest request, @retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.milkman.dairyapp.network.models.AddSupplierResponse>> $completion);
    
    @retrofit2.http.POST(value = "customers")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSupplier(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.network.models.AddSupplierRequest request, @retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.milkman.dairyapp.network.models.AddSupplierResponse>> $completion);
    
    @retrofit2.http.PUT(value = "customers/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCustomer(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String customerId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> request, @retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.milkman.dairyapp.network.models.AddSupplierResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "customers/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCustomer(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String customerId, @retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
}