package com.espmisti.klomodule.app

import android.app.Application
import android.util.Log
import com.espmisti.klo.Klo
import com.espmisti.klo.common.Constants

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initial KloModule
        Klo.Builder(context = this).initAppsflyer("okkpPZQHkxNotyrpigEDem")
    }
}