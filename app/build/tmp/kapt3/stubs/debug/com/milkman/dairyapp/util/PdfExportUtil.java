package com.milkman.dairyapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f\u00a8\u0006\u0010"}, d2 = {"Lcom/milkman/dairyapp/util/PdfExportUtil;", "", "()V", "exportMonthlyReport", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "month", "", "reportRows", "", "Lcom/milkman/dairyapp/data/model/MonthlyCustomerReport;", "includeAmount", "", "app_debug"})
public final class PdfExportUtil {
    @org.jetbrains.annotations.NotNull()
    public static final com.milkman.dairyapp.util.PdfExportUtil INSTANCE = null;
    
    private PdfExportUtil() {
        super();
    }
    
    public final void exportMonthlyReport(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    java.lang.String month, @org.jetbrains.annotations.NotNull()
    java.util.List<com.milkman.dairyapp.data.model.MonthlyCustomerReport> reportRows, boolean includeAmount) {
    }
}