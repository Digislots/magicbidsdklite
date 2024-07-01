package com.example.mylibrary

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.LinearLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class MagicBidSdkLite(private val context: Context) {
    private lateinit var adView: AdView
    lateinit var layout: LinearLayout
    private val adHandler = Handler(Looper.getMainLooper())
    private lateinit var adRunnable: Runnable
    private val adUnitIds = listOf(
        "ca-app-pub-3940256099942544/6300978111", // Test ad unit ID
        "/6499/example/banner" // Replace with your actual ad unit ID
    )
    private var currentAdUnitIndex = 0

    init {
        ApiSingleton.initialize(context)

        setupLayout()
    }

    private fun setupLayout() {
        layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }
    }

    fun rePlaceBannerAds() {
        adView = createAdView()
        layout.addView(adView)
        adRunnable = object : Runnable {
            override fun run() {
                layout.removeView(adView)
                currentAdUnitIndex = (currentAdUnitIndex + 1) % adUnitIds.size
                adView = createAdView()
                layout.addView(adView)
                adHandler.postDelayed(this, 3000) // 3 seconds delay
            }
        }
        adHandler.postDelayed(adRunnable, 3000)
    }

    private fun createAdView(): AdView {
        return AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = adUnitIds[currentAdUnitIndex]
            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    Log.d("MagicBidSdkLite", "Ad loaded successfully.")
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.e("MagicBidSdkLite", "Failed to load ad: ${error.message}")
                    adHandler.post(adRunnable)
                }
            }
            loadAd(AdRequest.Builder().build())
        }
    }

    fun cleanup() {
        adHandler.removeCallbacks(adRunnable)
        layout.removeAllViews()
    }
}
