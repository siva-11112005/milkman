package com.milkman.dairyapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\fH\u0002J\u000e\u0010\u0012\u001a\u00020\fH\u0082@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/milkman/dairyapp/ui/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/milkman/dairyapp/databinding/ActivityLoginBinding;", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "syncService", "Lcom/milkman/dairyapp/service/DataSyncService;", "viewModel", "Lcom/milkman/dairyapp/viewmodel/AuthViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openAdminDashboard", "openCustomerDashboard", "openDashboard", "syncPendingOperations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.milkman.dairyapp.databinding.ActivityLoginBinding binding;
    private com.milkman.dairyapp.viewmodel.AuthViewModel viewModel;
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    private com.milkman.dairyapp.service.DataSyncService syncService;
    
    public LoginActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.Object syncPendingOperations(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void openAdminDashboard() {
    }
    
    private final void openCustomerDashboard() {
    }
    
    private final void openDashboard() {
    }
}