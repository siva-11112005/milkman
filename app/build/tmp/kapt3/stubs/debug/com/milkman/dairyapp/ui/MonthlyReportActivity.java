package com.milkman.dairyapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/milkman/dairyapp/ui/MonthlyReportActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/milkman/dairyapp/databinding/ActivityMonthlyReportBinding;", "currentRows", "", "Lcom/milkman/dairyapp/data/model/MonthlyCustomerReport;", "exportPdfLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "reportAdapter", "Lcom/milkman/dairyapp/ui/adapters/MonthlyReportAdapter;", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "showAmount", "", "viewModel", "Lcom/milkman/dairyapp/viewmodel/ReportViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showMonthPicker", "app_debug"})
public final class MonthlyReportActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.milkman.dairyapp.databinding.ActivityMonthlyReportBinding binding;
    private com.milkman.dairyapp.viewmodel.ReportViewModel viewModel;
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    private com.milkman.dairyapp.ui.adapters.MonthlyReportAdapter reportAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.milkman.dairyapp.data.model.MonthlyCustomerReport> currentRows;
    private boolean showAmount = true;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> exportPdfLauncher = null;
    
    public MonthlyReportActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showMonthPicker() {
    }
}