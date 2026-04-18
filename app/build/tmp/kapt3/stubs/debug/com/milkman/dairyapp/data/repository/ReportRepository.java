package com.milkman.dairyapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J1\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ1\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ7\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ1\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ2\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u00062\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tJ1\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ2\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00100\u00062\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/milkman/dairyapp/data/repository/ReportRepository;", "", "milkEntryDao", "Lcom/milkman/dairyapp/data/dao/MilkEntryDao;", "(Lcom/milkman/dairyapp/data/dao/MilkEntryDao;)V", "dailyAmount", "Landroidx/lifecycle/LiveData;", "", "date", "", "entryType", "customerId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Landroidx/lifecycle/LiveData;", "dailyQuantity", "dailySessionBreakdown", "", "Lcom/milkman/dairyapp/data/model/DailySessionBreakdown;", "monthlyAmount", "month", "monthlyCustomerReport", "Lcom/milkman/dairyapp/data/model/MonthlyCustomerReport;", "customerCategory", "monthlyQuantity", "typeBreakdown", "Lcom/milkman/dairyapp/data/model/TypeBreakdown;", "app_debug"})
public final class ReportRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.dao.MilkEntryDao milkEntryDao = null;
    
    public ReportRepository(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.data.dao.MilkEntryDao milkEntryDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> dailyQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> dailyAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> monthlyQuantity(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> monthlyAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.TypeBreakdown>> typeBreakdown(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.MonthlyCustomerReport>> monthlyCustomerReport(@org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.Nullable()
    java.lang.String customerCategory, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.milkman.dairyapp.data.model.DailySessionBreakdown>> dailySessionBreakdown(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.Nullable()
    java.lang.String entryType, @org.jetbrains.annotations.Nullable()
    java.lang.Integer customerId) {
        return null;
    }
}