package com.milkman.dairyapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J \u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0012\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u001a\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002J\b\u0010 \u001a\u00020\u000eH\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020#H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/milkman/dairyapp/ui/SuperUserProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adminList", "", "Lcom/milkman/dairyapp/network/models/AdminResponse;", "btnAddAdmin", "Lcom/google/android/material/button/MaterialButton;", "btnLogout", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "confirmDeleteAdmin", "", "admin", "createAdmin", "username", "", "password", "fullName", "deleteAdmin", "formatPermissionKey", "rawKey", "loadAdmins", "logout", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "parseErrorMessage", "rawBody", "fallbackMessage", "showAddAdminDialog", "showAdminDetailsDialog", "showAdminDetailsDialogInternal", "Lcom/milkman/dairyapp/network/models/AdminDetailsResponse;", "app_debug"})
public final class SuperUserProfileActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private com.google.android.material.button.MaterialButton btnAddAdmin;
    private com.google.android.material.button.MaterialButton btnLogout;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.milkman.dairyapp.network.models.AdminResponse> adminList;
    
    public SuperUserProfileActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadAdmins() {
    }
    
    private final void showAdminDetailsDialog(com.milkman.dairyapp.network.models.AdminResponse admin) {
    }
    
    private final void showAdminDetailsDialogInternal(com.milkman.dairyapp.network.models.AdminDetailsResponse admin) {
    }
    
    private final void showAddAdminDialog() {
    }
    
    private final void createAdmin(java.lang.String username, java.lang.String password, java.lang.String fullName) {
    }
    
    private final void confirmDeleteAdmin(com.milkman.dairyapp.network.models.AdminResponse admin) {
    }
    
    private final void deleteAdmin(com.milkman.dairyapp.network.models.AdminResponse admin) {
    }
    
    private final java.lang.String parseErrorMessage(java.lang.String rawBody, java.lang.String fallbackMessage) {
        return null;
    }
    
    private final java.lang.String formatPermissionKey(java.lang.String rawKey) {
        return null;
    }
    
    private final void logout() {
    }
}