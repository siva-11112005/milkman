package com.milkman.dairyapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/milkman/dairyapp/util/NetworkConnectivityObserver;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isNetworkMetered", "", "connectivityManager", "Landroid/net/ConnectivityManager;", "network", "Landroid/net/Network;", "observeConnectivity", "Lkotlinx/coroutines/flow/Flow;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus;", "ConnectivityStatus", "app_debug"})
public final class NetworkConnectivityObserver {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public NetworkConnectivityObserver(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus> observeConnectivity() {
        return null;
    }
    
    private final boolean isNetworkMetered(android.net.ConnectivityManager connectivityManager, android.net.Network network) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus;", "", "()V", "Connected", "Disconnected", "MeteredConnected", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$Connected;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$Disconnected;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$MeteredConnected;", "app_debug"})
    public static abstract class ConnectivityStatus {
        
        private ConnectivityStatus() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$Connected;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus;", "()V", "app_debug"})
        public static final class Connected extends com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus.Connected INSTANCE = null;
            
            private Connected() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$Disconnected;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus;", "()V", "app_debug"})
        public static final class Disconnected extends com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus.Disconnected INSTANCE = null;
            
            private Disconnected() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus$MeteredConnected;", "Lcom/milkman/dairyapp/util/NetworkConnectivityObserver$ConnectivityStatus;", "()V", "app_debug"})
        public static final class MeteredConnected extends com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.milkman.dairyapp.util.NetworkConnectivityObserver.ConnectivityStatus.MeteredConnected INSTANCE = null;
            
            private MeteredConnected() {
            }
        }
    }
}