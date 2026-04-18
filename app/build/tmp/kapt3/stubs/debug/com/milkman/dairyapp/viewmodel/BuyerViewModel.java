package com.milkman.dairyapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J6\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u001e\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0015J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0015J>\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/milkman/dairyapp/viewmodel/BuyerViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_actionState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "actionState", "Landroidx/lifecycle/LiveData;", "getActionState", "()Landroidx/lifecycle/LiveData;", "buyers", "", "Lcom/milkman/dairyapp/data/entity/CustomerEntity;", "getBuyers", "db", "Lcom/milkman/dairyapp/data/AppDatabase;", "repository", "Lcom/milkman/dairyapp/data/repository/CustomerRepository;", "searchText", "", "kotlin.jvm.PlatformType", "addBuyer", "", "name", "phone", "address", "type", "sellingPricePerLiter", "", "userId", "", "deleteBuyer", "item", "authToken", "setSearchText", "query", "updateBuyer", "buyerId", "app_debug"})
public final class BuyerViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.CustomerRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> searchText = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> buyers = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.milkman.dairyapp.data.model.RepositoryResult> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> actionState = null;
    
    public BuyerViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getBuyers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> getActionState() {
        return null;
    }
    
    public final void setSearchText(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void addBuyer(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String type, double sellingPricePerLiter, int userId) {
    }
    
    public final void updateBuyer(int buyerId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String type, double sellingPricePerLiter, int userId) {
    }
    
    public final void deleteBuyer(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.entity.CustomerEntity item, int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String authToken) {
    }
}