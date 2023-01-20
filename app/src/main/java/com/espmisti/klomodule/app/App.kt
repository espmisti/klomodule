package com.espmisti.klomodule.app

import android.app.Application
import com.espmisti.klo.KloService

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KloService.Builder()
            .setAFKey(v = "okkpPZQHkxNotyrpigEDem")
            .init(context = this)
    }
}