package com.nursultan.adstest

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nursultan.adstest.databinding.ActivityMainBinding


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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        path = getString()
        start()
    }

    private fun start() {
        if (path.isNullOrEmpty()) {
            fireLoad()
        } else {

        }

    }

    private fun fireLoad() {
        val getUrl = remoteConfig.getUrl()
        val brandDevice = Build.MANUFACTURER
        val isSim = checkIsSimAvailable()
        if (getUrl.isEmpty() || brandDevice.lowercase().contains(GOOGLE_DEVICE) || !isSim) {
            launchDefaultFragment()
        } else
            launchWebViewFragment()
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

    private fun launchDefaultFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DefaultFragment.newInstance())
            .commit()
    }

    private fun launchWebViewFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, WebViewFragment.newInstance())
            .commit()
    }

    companion object {
        const val STORAGE_NAME = "data"
        const val URL_KEY = "url"
        const val GOOGLE_DEVICE = "google"
    }
}