package com.espmisti.klo

import android.content.Context
import android.util.Log
import com.espmisti.common.Constants
import com.espmisti.common.Utils
import com.espmisti.data.repository.AppsflyerRepositoryImpl
import com.espmisti.domain.usecase.GetAppsflyerDataUseCase
import com.espmisti.domain.usecase.GetAppsflyerStateUseCase
import com.espmisti.domain.usecase.InitAppsflyerUseCase

object Klo {
    class Builder(private val context: Context) {

        // Init Appsflyer
        fun initAppsflyer(af_key: String) = apply {
            if (Utils.isNetworkAvailable(context)) {
                InitAppsflyerUseCase(repository = AppsflyerRepositoryImpl(context)).execute(key = af_key)
                Utils.prefSaveString(context, Constants.PREFS.AF_KEY, af_key)
            } else Log.e(Constants.TAG, "[Error]: Internet connection failure")
        }

        // Get Campaign
        suspend fun getCampaign() : List<String>? {
            if (Utils.isNetworkAvailable(context)) {
                val af_key = Utils.prefGetString(context, Constants.PREFS.AF_KEY)
                af_key?.let {
                    if (GetAppsflyerStateUseCase(repository = AppsflyerRepositoryImpl(context)).execute(v = it)) {
                        return GetAppsflyerDataUseCase(repository = AppsflyerRepositoryImpl(context = context)).execute()
                    } else {
                        Log.e(Constants.TAG, "[Error]: Appsflyer is not initialized")
                        return null
                    }
                } ?: run {
                    Log.e(Constants.TAG, "[Error]: af_key is empty")
                    return null
                }
            } else {
                Log.e(Constants.TAG, "[Error]: Internet connection failure")
                return null
            }
        }
    }
}