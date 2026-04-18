package com.milkman.dairyapp.data.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bo\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0006H\u00c6\u0003J\t\u0010-\u001a\u00020\u0006H\u00c6\u0003J\t\u0010.\u001a\u00020\u0006H\u00c6\u0003J\t\u0010/\u001a\u00020\nH\u00c6\u0003J\t\u00100\u001a\u00020\nH\u00c6\u0003J\t\u00101\u001a\u00020\nH\u00c6\u0003J\t\u00102\u001a\u00020\u000eH\u00c6\u0003J\u008a\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00104J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00108\u001a\u00020\u0003H\u00d6\u0001J\t\u00109\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%\u00a8\u0006:"}, d2 = {"Lcom/milkman/dairyapp/data/entity/MilkEntryEntity;", "", "id", "", "customerId", "entryDate", "", "session", "entryType", "quantityLiters", "", "pricePerLiter", "amount", "createdAt", "", "createdBy", "updatedAt", "updatedBy", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDJILjava/lang/Long;Ljava/lang/Integer;)V", "getAmount", "()D", "getCreatedAt", "()J", "getCreatedBy", "()I", "getCustomerId", "getEntryDate", "()Ljava/lang/String;", "getEntryType", "getId", "getPricePerLiter", "getQuantityLiters", "getSession", "getUpdatedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUpdatedBy", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDJILjava/lang/Long;Ljava/lang/Integer;)Lcom/milkman/dairyapp/data/entity/MilkEntryEntity;", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "milk_entries", foreignKeys = {@androidx.room.ForeignKey(entity = com.milkman.dairyapp.data.entity.CustomerEntity.class, parentColumns = {"id"}, childColumns = {"customerId"}, onDelete = 5, onUpdate = 5)}, indices = {@androidx.room.Index(value = {"customerId"}), @androidx.room.Index(value = {"entryDate"}), @androidx.room.Index(value = {"customerId", "entryDate", "session", "entryType"}, unique = true)})
public final class MilkEntryEntity {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final int id = 0;
    private final int customerId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entryDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String session = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entryType = null;
    private final double quantityLiters = 0.0;
    private final double pricePerLiter = 0.0;
    private final double amount = 0.0;
    private final long createdAt = 0L;
    private final int createdBy = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long updatedAt = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer updatedBy = null;
    
    public MilkEntryEntity(int id, int customerId, @org.jetbrains.annotations.NotNull()
    java.lang.String entryDate, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String entryType, double quantityLiters, double pricePerLiter, double amount, long createdAt, int createdBy, @org.jetbrains.annotations.Nullable()
    java.lang.Long updatedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Integer updatedBy) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    public final int getCustomerId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntryDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSession() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntryType() {
        return null;
    }
    
    public final double getQuantityLiters() {
        return 0.0;
    }
    
    public final double getPricePerLiter() {
        return 0.0;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final int getCreatedBy() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUpdatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getUpdatedBy() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component12() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.milkman.dairyapp.data.entity.MilkEntryEntity copy(int id, int customerId, @org.jetbrains.annotations.NotNull()
    java.lang.String entryDate, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String entryType, double quantityLiters, double pricePerLiter, double amount, long createdAt, int createdBy, @org.jetbrains.annotations.Nullable()
    java.lang.Long updatedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Integer updatedBy) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}