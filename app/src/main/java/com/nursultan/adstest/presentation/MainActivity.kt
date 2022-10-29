package com.nursultan.adstest.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nursultan.adstest.R
import com.nursultan.adstest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ViewBinding
    private lateinit var remoteConfigUtil: RemoteConfigUtil
    private val sharedPreferences by lazy {
        application.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }
    private var path: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchLoadingScreen()
        remoteConfigUtil = (application as AdsApp).remoteConfigUtil
        path = getString()
        start()
    }

    private fun launchLoadingScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,LoadingFragment.newInstance())
            .commit()
    }

    private fun start() {
        if (path.isNullOrEmpty()) {
            setFetchCompleteObserver()
        } else {
            path?.let {
                launchWebViewFragment(it)
            }
        }
    }

    private fun fireLoad() {
        val getUrl = remoteConfigUtil.getUrl()
        val brandDevice = Build.MANUFACTURER
        val isSim = checkIsSimAvailable()
        if (getUrl.isEmpty() || brandDevice.lowercase().contains(GOOGLE_DEVICE) || !isSim) {
            launchDefaultFragment()
        } else launchWebViewFragment(getUrl)
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

    private fun setFetchCompleteObserver() {
        remoteConfigUtil.remoteConfig.fetchAndActivate().addOnCompleteListener {
            fireLoad()
        }
    }


    private fun getString(): String? {
        return sharedPreferences.getString(RemoteConfigUtil.URL_KEY, null)
    }

    private fun launchDefaultFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, DefaultFragment.newInstance()).commit()
    }

    private fun launchWebViewFragment(url: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, WebViewFragment.newInstance(url)).commit()
    }

    companion object {
        const val STORAGE_NAME = "data"
        const val GOOGLE_DEVICE = "google"
    }
}