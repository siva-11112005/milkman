package com.milkman.dairyapp.ui.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bBA\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0014\u0010\u0018\u001a\u00020\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/milkman/dairyapp/ui/adapters/MilkEntryAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/milkman/dairyapp/ui/adapters/MilkEntryAdapter$MilkEntryViewHolder;", "canModify", "", "userRole", "", "onEdit", "Lkotlin/Function1;", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "", "onDelete", "(ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "items", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "newItems", "", "MilkEntryViewHolder", "app_debug"})
public final class MilkEntryAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.milkman.dairyapp.ui.adapters.MilkEntryAdapter.MilkEntryViewHolder> {
    private final boolean canModify = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userRole = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onEdit = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onDelete = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer> items = null;
    
    public MilkEntryAdapter(boolean canModify, @org.jetbrains.annotations.NotNull()
    java.lang.String userRole, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onDelete) {
        super();
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.milkman.dairyapp.data.model.MilkEntryWithCustomer> newItems) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.milkman.dairyapp.ui.adapters.MilkEntryAdapter.MilkEntryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.milkman.dairyapp.ui.adapters.MilkEntryAdapter.MilkEntryViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\nR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/milkman/dairyapp/ui/adapters/MilkEntryAdapter$MilkEntryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "canModify", "", "userRole", "", "onEdit", "Lkotlin/Function1;", "Lcom/milkman/dairyapp/data/model/MilkEntryWithCustomer;", "", "onDelete", "(Landroid/view/View;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "btnDelete", "Lcom/google/android/material/button/MaterialButton;", "btnEdit", "tvAmount", "Landroid/widget/TextView;", "tvLine1", "tvLine2", "tvLockStatus", "bind", "item", "app_debug"})
    public static final class MilkEntryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final boolean canModify = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userRole = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onEdit = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onDelete = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvLine1 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvLine2 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvAmount = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvLockStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.button.MaterialButton btnEdit = null;
        @org.jetbrains.annotations.NotNull()
        private final com.google.android.material.button.MaterialButton btnDelete = null;
        
        public MilkEntryViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView, boolean canModify, @org.jetbrains.annotations.NotNull()
        java.lang.String userRole, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.milkman.dairyapp.data.model.MilkEntryWithCustomer, kotlin.Unit> onDelete) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.milkman.dairyapp.data.model.MilkEntryWithCustomer item) {
        }
    }
}