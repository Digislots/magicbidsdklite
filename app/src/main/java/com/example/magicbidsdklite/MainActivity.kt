package com.example.magicbidsdklite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.MagicBidSdkLite

class MainActivity : AppCompatActivity() {
    private lateinit var magicBidSdkLite: MagicBidSdkLite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        magicBidSdkLite = MagicBidSdkLite(this)

        magicBidSdkLite.rePlaceBannerAds()
        setContentView(magicBidSdkLite.layout)
    }
}