package com.espmisti.klo.data.repository

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener
import com.espmisti.klo.common.Constants
import com.espmisti.klo.domain.repository.AppsflyerRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AppsflyerRepositoryImpl(private val context: Context) : AppsflyerRepository {

    override suspend fun getInitState(key: String): Boolean = suspendCoroutine {
        AppsFlyerLib.getInstance().start(context, key, object : AppsFlyerRequestListener {
            override fun onSuccess() { it.resume(true) }
            override fun onError(code: Int, error: String) {
                Log.e(Constants.TAG, "[AppsflyerRepositoryImpl]: ($code) $error")
                it.resume(false)
            }
        })
    }

    override suspend fun getData(): MutableMap<String, Any>? = suspendCoroutine {
        val conversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(map: MutableMap<String, Any>?) {
                Log.i(Constants.TAG, "onConversionDataSuccess: $map")
                it.resume(map)
            }
            override fun onConversionDataFail(p0: String?) {
                Log.e(Constants.TAG, "[onConversionDataFail]: $p0")
                it.resume(null)
            }
            override fun onAppOpenAttribution(map: MutableMap<String, String>?) { it.resume(null) }
            override fun onAttributionFailure(p0: String?) {
                Log.e(Constants.TAG, "[onAttributionFailure]: $p0")
                it.resume(null)
            }
        }
        AppsFlyerLib.getInstance().registerConversionListener(context, conversionListener)
    }
}