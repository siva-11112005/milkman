package com.milkman.dairyapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/milkman/dairyapp/viewmodel/DashboardViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "db", "Lcom/milkman/dairyapp/data/AppDatabase;", "milkRepository", "Lcom/milkman/dairyapp/data/repository/MilkRepository;", "getAdminDailySummary", "Landroidx/lifecycle/LiveData;", "Lcom/milkman/dairyapp/viewmodel/AdminDailySummary;", "date", "", "getCustomerDailySummary", "Lcom/milkman/dairyapp/viewmodel/CustomerDailySummary;", "supplierId", "", "toAdminSummary", "", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.MilkRepository milkRepository = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.viewmodel.AdminDailySummary> getAdminDailySummary(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.viewmodel.CustomerDailySummary> getCustomerDailySummary(@org.jetbrains.annotations.NotNull()
    java.lang.String date, int supplierId) {
        return null;
    }
    
    private final com.milkman.dairyapp.viewmodel.AdminDailySummary toAdminSummary(java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer> $this$toAdminSummary) {
        return null;
    }
}