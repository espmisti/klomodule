package com.espmisti.klomodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.espmisti.klo.Klo
import com.espmisti.klo.common.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            val campaign = Klo.Builder(context = applicationContext).getCampaign()
            Log.i(Constants.TAG, "[Result]: $campaign")
        }
    }
}