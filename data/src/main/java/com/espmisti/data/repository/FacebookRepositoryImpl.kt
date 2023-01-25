package com.espmisti.data.repository

import android.content.Context
import android.content.Intent
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FacebookRepositoryImpl(private val context: Context, private val intent: Intent) :
    com.espmisti.domain.repository.FacebookRepository {
    override fun getInitState(id: String, token: String): Boolean {
        FacebookSdk.apply {
            setApplicationId(id)
            setClientToken(token)
            sdkInitialize(context)
            setAutoInitEnabled(true)
            fullyInitialize()
            return isInitialized()
        }
    }

    override suspend fun getData(): String? = suspendCoroutine{
        AppLinkData.fetchDeferredAppLinkData(context, AppLinkData.CompletionHandler { appLink ->
            if (appLink?.targetUri != null) it.resume(appLink.targetUri.toString())
            else it.resume(null)
        })
    }
}