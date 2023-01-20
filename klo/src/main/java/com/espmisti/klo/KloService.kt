package com.espmisti.klo

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.espmisti.klo.common.Constants
import com.espmisti.klo.data.repository.AppsflyerRepositoryImpl
import com.espmisti.klo.domain.repository.AppsflyerRepository
import com.espmisti.klo.domain.usecase.GetAppsflyerDataUseCase
import com.espmisti.klo.domain.usecase.GetAppsflyerStateUseCase

data class KloService(
    val af_key: String? = null
) {
    class Builder(var af_key: String? = null) {

        // Set Appsflyer Key
        fun setAFKey(v: String) = apply { af_key = v }

        // Init Appsflyer
        fun init(context: Context) {
            af_key?.let {
                AppsFlyerLib.getInstance().init(it, null, context)
            } ?: run {
                Log.e(Constants.TAG, "[Klo Service]: AF_KEY is empty. Use setAFKey(value)")
            }
        }
        //
        suspend fun getCampaign(context: Context) : String? {
            af_key?.let {
                return if (GetAppsflyerStateUseCase(repository = AppsflyerRepositoryImpl(context = context)).execute(v = it)) {
                    GetAppsflyerDataUseCase(repository = AppsflyerRepositoryImpl(context = context)).execute()
                } else null
            } ?: run {
                Log.e(Constants.TAG, "[Klo Service]: AF_KEY is empty. Use setAFKey(value)")
                return null
            }
        }

    }
}