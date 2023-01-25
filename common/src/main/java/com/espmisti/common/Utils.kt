package com.espmisti.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


object Utils {
    // Shared
    fun prefSaveString(context: Context, key: String, value: String) {
        val pref = context.getSharedPreferences(Constants.PREFS.SETTINGS, Context.MODE_PRIVATE)
        pref.edit().apply{
            putString(key, value)
            apply()
        }
    }
    fun prefGetString(context: Context, key: String) : String? {
        val pref = context.getSharedPreferences(Constants.PREFS.SETTINGS, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }
    // Check Internet Connection
    fun isNetworkAvailable(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) return true
        }
        return false
    }
}