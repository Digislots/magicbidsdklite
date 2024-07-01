package com.example.mylibrary
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.MobileAds



object ApiSingleton {


    fun initialize(context: Context) {
        MobileAds.initialize(context) { initializationStatus ->
            val statusMap =
                initializationStatus.adapterStatusMap
            for (adapterClass in statusMap.keys) {
                val status = statusMap[adapterClass]
                Log.d(
                    "MyApp", String.format(
                        "Adapter name: %s, Description: %s, Latency: %d",
                        adapterClass, status!!.description, status.latency
                    )
                )
                Log.d("MagicBidSdkLite", "AdMob SDK initialized.")
            }

            // Start loading ads here...
        }

    }

}

