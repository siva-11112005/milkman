package com.milkman.dairyapp.ui.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0017H\u0016J\u001a\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010&\u001a\u00020\u0017H\u0002J\b\u0010\'\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/milkman/dairyapp/ui/fragments/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/milkman/dairyapp/databinding/FragmentHomeBinding;", "adminSummaryLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/milkman/dairyapp/viewmodel/AdminDailySummary;", "binding", "getBinding", "()Lcom/milkman/dairyapp/databinding/FragmentHomeBinding;", "customerSummaryLiveData", "Lcom/milkman/dairyapp/viewmodel/CustomerDailySummary;", "linkedSupplierId", "", "Ljava/lang/Integer;", "selectedDate", "", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "viewModel", "Lcom/milkman/dairyapp/viewmodel/DashboardViewModel;", "bindAdminSummary", "", "summary", "bindCustomerSummary", "loadDataForDate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "resolveSupplierAndLoad", "updateDateDisplay", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.milkman.dairyapp.databinding.FragmentHomeBinding _binding;
    private com.milkman.dairyapp.viewmodel.DashboardViewModel viewModel;
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String selectedDate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer linkedSupplierId;
    @org.jetbrains.annotations.Nullable()
    private androidx.lifecycle.LiveData<com.milkman.dairyapp.viewmodel.AdminDailySummary> adminSummaryLiveData;
    @org.jetbrains.annotations.Nullable()
    private androidx.lifecycle.LiveData<com.milkman.dairyapp.viewmodel.CustomerDailySummary> customerSummaryLiveData;
    
    public HomeFragment() {
        super();
    }
    
    private final com.milkman.dairyapp.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void resolveSupplierAndLoad() {
    }
    
    private final void updateDateDisplay() {
    }
    
    private final void loadDataForDate() {
    }
    
    private final void bindAdminSummary(com.milkman.dairyapp.viewmodel.AdminDailySummary summary) {
    }
    
    private final void bindCustomerSummary(com.milkman.dairyapp.viewmodel.CustomerDailySummary summary) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}