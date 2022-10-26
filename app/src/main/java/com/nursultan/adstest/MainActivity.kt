package com.nursultan.adstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nursultan.adstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val remoteConfig by lazy {
        (application as AdsApp).remoteConfigUtil
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvMain.text = remoteConfig.getUrl()


    }
}