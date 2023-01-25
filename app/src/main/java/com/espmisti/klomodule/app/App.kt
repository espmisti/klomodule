package com.espmisti.klomodule.app

import android.app.Application
import com.espmisti.klo.Klo

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initial KloModule
        Klo.Builder(context = this).initAppsflyer("okkpPZQHkxNotyrpigEDem")
    }
}