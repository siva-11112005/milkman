package com.milkman.dairyapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkConnectivityObserver(private val context: Context) {

    fun observeConnectivity(): Flow<ConnectivityStatus> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val isMetered = isNetworkMetered(connectivityManager, network)
                trySend(if (isMetered) ConnectivityStatus.MeteredConnected else ConnectivityStatus.Connected)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(ConnectivityStatus.Disconnected)
            }

            override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
                super.onCapabilitiesChanged(network, capabilities)
                val isMetered = !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                trySend(if (isMetered) ConnectivityStatus.MeteredConnected else ConnectivityStatus.Connected)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    private fun isNetworkMetered(connectivityManager: ConnectivityManager, network: Network): Boolean {
        val notMetered = connectivityManager.getNetworkCapabilities(network)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        return notMetered != true
    }

    sealed class ConnectivityStatus {
        object Connected : ConnectivityStatus()
        object MeteredConnected : ConnectivityStatus()
        object Disconnected : ConnectivityStatus()
    }
}
