package com.milkman.dairyapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018JE\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%\u00a2\u0006\u0002\u0010&R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\f\u0012\n\u0018\u00010\tj\u0004\u0018\u0001`\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0018\u00010\tj\u0004\u0018\u0001`\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/milkman/dairyapp/viewmodel/ProfileViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_actionState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/milkman/dairyapp/data/model/RepositoryResult;", "_profile", "Lcom/milkman/dairyapp/data/model/User;", "Lcom/milkman/dairyapp/data/entity/UserEntity;", "actionState", "Landroidx/lifecycle/LiveData;", "getActionState", "()Landroidx/lifecycle/LiveData;", "db", "Lcom/milkman/dairyapp/data/AppDatabase;", "profile", "getProfile", "repository", "Lcom/milkman/dairyapp/data/repository/ProfileRepository;", "changePassword", "", "userId", "", "currentPassword", "", "newPassword", "actorUserId", "loadProfile", "updateProfile", "fullName", "phone", "address", "pricePerLiter", "", "canEditPrice", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;IZ)V", "app_debug"})
public final class ProfileViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.milkman.dairyapp.data.repository.ProfileRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.milkman.dairyapp.data.model.User> _profile = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.User> profile = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.milkman.dairyapp.data.model.RepositoryResult> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> actionState = null;
    
    public ProfileViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.User> getProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.milkman.dairyapp.data.model.RepositoryResult> getActionState() {
        return null;
    }
    
    public final void loadProfile(int userId) {
    }
    
    public final void updateProfile(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.Nullable()
    java.lang.Double pricePerLiter, int actorUserId, boolean canEditPrice) {
    }
    
    public final void changePassword(int userId, @org.jetbrains.annotations.NotNull()
    java.lang.String currentPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, int actorUserId) {
    }
}