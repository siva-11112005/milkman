package com.milkman.dairyapp.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/milkman/dairyapp/ui/AddMilkEntryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/milkman/dairyapp/databinding/ActivityAddMilkEntryBinding;", "entryType", "", "profiles", "", "Lcom/milkman/dairyapp/data/entity/CustomerEntity;", "sessionManager", "Lcom/milkman/dairyapp/util/SessionManager;", "viewModel", "Lcom/milkman/dairyapp/viewmodel/MilkEntryViewModel;", "observeProfiles", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveEntry", "setupDateField", "setupSessionSpinner", "setupToolbar", "suggestSessionByTime", "updatePreviewAmount", "Companion", "app_debug"})
public final class AddMilkEntryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.milkman.dairyapp.databinding.ActivityAddMilkEntryBinding binding;
    private com.milkman.dairyapp.viewmodel.MilkEntryViewModel viewModel;
    private com.milkman.dairyapp.util.SessionManager sessionManager;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.milkman.dairyapp.data.entity.CustomerEntity> profiles;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String entryType = "COLLECTION";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ENTRY_TYPE = "entry_type";
    @org.jetbrains.annotations.NotNull()
    public static final com.milkman.dairyapp.ui.AddMilkEntryActivity.Companion Companion = null;
    
    public AddMilkEntryActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupSessionSpinner() {
    }
    
    private final void suggestSessionByTime() {
    }
    
    private final void setupDateField() {
    }
    
    private final void observeProfiles() {
    }
    
    private final void saveEntry() {
    }
    
    private final void updatePreviewAmount() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/milkman/dairyapp/ui/AddMilkEntryActivity$Companion;", "", "()V", "EXTRA_ENTRY_TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}