package com.milkman.dairyapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J.\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020%J.\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020%J6\u0010.\u001a\u00020#2\u0006\u0010/\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u00100\u001a\u00020\'2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020%J\u0016\u00101\u001a\u00020#2\u0006\u00102\u001a\u00020%2\u0006\u0010+\u001a\u00020%J\u000e\u00103\u001a\u0002042\u0006\u00105\u001a\u000206JG\u00107\u001a\u00020#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\'2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010%2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\'2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\'2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\'\u00a2\u0006\u0002\u0010:J\u001e\u0010;\u001a\u00020#2\u0006\u00102\u001a\u00020%2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020%R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000f0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000bR\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u001c0\u001c0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000b\u00a8\u0006<"}, d2 = {"Lcom/milkman/dairyapp/viewmodel/MilkEntryViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_actionState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "actionState", "Landroidx/lifecycle/LiveData;", "getActionState", "()Landroidx/lifecycle/LiveData;", "auditRepository", "Lcom/milkman/dairyapp/data/repository/AuditRepository;", "buyers", "", "Lcom/milkman/dairyapp/data/entity/CustomerEntity;", "getBuyers", "customerRepository", "Lcom/milkman/dairyapp/data/repository/CustomerRepository;", "customers", "getCustomers", "db", "Lcom/milkman/dairyapp/data/AppDatabase;", "entries", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "getEntries", "filters", "Lcom/milkman/dairyapp/viewmodel/EntryFilterState;", "kotlin.jvm.PlatformType", "milkRepository", "Lcom/milkman/dairyapp/data/repository/MilkRepository;", "suppliers", "getSuppliers", "addCollectionEntry", "", "supplierId", "", "date", "", "session", "quantity", "", "userId", "addDistributionEntry", "buyerId", "addEntry", "customerId", "entryType", "deleteEntry", "entryId", "isLocked", "", "createdAt", "", "setFilters", "customerCategory", "customerType", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "updateEntry", "app_debug"})
public final class MilkEntryViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.AuditRepository auditRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.MilkRepository milkRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.CustomerRepository customerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.milkman.dairyapp.viewmodel.EntryFilterState> filters = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> entries = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> suppliers = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> buyers = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> customers = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.milkman.dairyapp.data.model.RepositoryResult> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> actionState = null;
    
    public MilkEntryViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer>> getEntries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getSuppliers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getBuyers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity>> getCustomers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> getActionState() {
        return null;
    }
    
    public final void setFilters(@org.jetbrains.annotations.Nullable()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String customerType, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType) {
    }
    
    public final void addCollectionEntry(int supplierId, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String session, double quantity, int userId) {
    }
    
    public final void addDistributionEntry(int buyerId, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String session, double quantity, int userId) {
    }
    
    public final void addEntry(int customerId, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String entryType, double quantity, int userId) {
    }
    
    public final void updateEntry(int entryId, double quantity, int userId) {
    }
    
    public final void deleteEntry(int entryId, int userId) {
    }
    
    public final boolean isLocked(long createdAt) {
        return false;
    }
}