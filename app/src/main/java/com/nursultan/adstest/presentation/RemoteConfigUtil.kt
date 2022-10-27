package com.nursultan.adstest.presentation


import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.nursultan.adstest.BuildConfig

class RemoteConfigUtil {

    private lateinit var remoteConfig: FirebaseRemoteConfig

   fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                DEBUG_TIME_IN_SECONDS
            } else {
                MINIMUM_FETCH_TIME
            }
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            fetchAndActivate().addOnCanceledListener {
                Log.d("RemoteConfigUtil", "Remote Config fetch completed")
            }
        }
        return remoteConfig
    }

    fun getUrl(): String = remoteConfig.getString(URL_KEY)


    companion object {
        const val URL_KEY = "url"
        private const val DEBUG_TIME_IN_SECONDS = 0L
        private const val MINIMUM_FETCH_TIME = 3600L
    }

}