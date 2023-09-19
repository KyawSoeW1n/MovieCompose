package com.kurio.tetsuya.movie.compose.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isOnline(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    } else {
        @Suppress("DEPRECATION")
        return connectivityManager.activeNetworkInfo != null &&
                connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
    }
}
