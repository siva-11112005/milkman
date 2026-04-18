package com.milkman.dairyapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000bJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/milkman/dairyapp/util/TimeUtils;", "", "()V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "dateTimeFormatter", "monthFormatter", "currentDate", "", "currentMonth", "currentTimestamp", "", "dateFromPicker", "year", "", "monthZeroBased", "day", "formatDate", "millis", "formatDateTime", "isLocked", "", "createdAt", "userRole", "monthFromDate", "date", "toDisplayDate", "app_debug"})
public final class TimeUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat dateFormatter = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat monthFormatter = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.SimpleDateFormat dateTimeFormatter = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.milkman.dairyapp.util.TimeUtils INSTANCE = null;
    
    private TimeUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDate(long millis) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDateTime(long millis) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String monthFromDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    public final long currentTimestamp() {
        return 0L;
    }
    
    public final boolean isLocked(long createdAt, @org.jetbrains.annotations.NotNull()
    java.lang.String userRole) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toDisplayDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dateFromPicker(int year, int monthZeroBased, int day) {
        return null;
    }
}