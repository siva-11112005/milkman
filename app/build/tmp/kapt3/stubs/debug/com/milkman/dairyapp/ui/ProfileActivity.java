package com.milkman.dairyapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0011H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/milkman/dairyapp/ui/ProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adminList", "", "Lcom/milkman/dairyapp/network/models/AdminResponse;", "binding", "Lcom/milkman/dairyapp/databinding/ActivityProfileBinding;", "isAdmin", "", "isSuperUser", "selectedAdmin", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "viewModel", "Lcom/milkman/dairyapp/viewmodel/ProfileViewModel;", "formatPermissionKey", "", "rawKey", "loadAdminDetails", "", "admin", "loadAdminsForSelector", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAdminDetails", "Lcom/milkman/dairyapp/network/models/AdminDetailsResponse;", "switchToAdmin", "updateSelectedAdminProfile", "fullName", "app_debug"})
public final class ProfileActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.milkman.dairyapp.databinding.ActivityProfileBinding binding;
    private com.milkman.dairyapp.viewmodel.ProfileViewModel viewModel;
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.milkman.dairyapp.network.models.AdminResponse> adminList;
    @org.jetbrains.annotations.Nullable()
    private com.milkman.dairyapp.network.models.AdminResponse selectedAdmin;
    private boolean isSuperUser = false;
    private boolean isAdmin = false;
    
    public ProfileActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadAdminsForSelector() {
    }
    
    private final void loadAdminDetails(com.milkman.dairyapp.network.models.AdminResponse admin) {
    }
    
    private final void showAdminDetails(com.milkman.dairyapp.network.models.AdminDetailsResponse admin) {
    }
    
    private final void updateSelectedAdminProfile(java.lang.String fullName) {
    }
    
    private final java.lang.String formatPermissionKey(java.lang.String rawKey) {
        return null;
    }
    
    private final void switchToAdmin(com.milkman.dairyapp.network.models.AdminResponse admin) {
    }
}