package com.nursultan.adstest

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nursultan.adstest.databinding.ActivityMainBinding
import com.nursultan.adstest.databinding.MainWebviewBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ViewBinding
    private val remoteConfig by lazy {
        (application as AdsApp).remoteConfigUtil
    }
    private val sharedPreferences by lazy {
        application.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }
    private var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        path = getString()
        start()
        setContentView(binding.root)
    }

    private fun start() {
        if (path.isNullOrEmpty()) {
            binding = fireLoad()
        } else {

        }

    }

    private fun fireLoad(): ViewBinding {
        val getUrl = remoteConfig.getUrl()
        val brandDevice = Build.MANUFACTURER
        val isSim = checkIsSimAvailable()
        return if (getUrl.isEmpty() || brandDevice.lowercase().contains(GOOGLE_DEVICE) || !isSim) {
            ActivityMainBinding.inflate(layoutInflater)
        } else
            MainWebviewBinding.inflate(layoutInflater)
    }

    private fun checkIsSimAvailable(): Boolean {
        val tm = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        return when (tm.simState) {
            TelephonyManager.SIM_STATE_ABSENT -> false
            else -> {
                true
            }
        }
    }

    private fun setValue(value: String) {
        sharedPreferences.edit().apply {
            putString(URL_KEY, value)
        }.apply()
    }

    private fun getString(): String? {
        return sharedPreferences.getString(URL_KEY, null)
    }

    companion object {
        const val STORAGE_NAME = "data"
        const val URL_KEY = "url"
        const val GOOGLE_DEVICE = "google"
    }
}