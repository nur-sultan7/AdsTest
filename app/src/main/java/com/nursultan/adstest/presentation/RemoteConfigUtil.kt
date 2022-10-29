package com.nursultan.adstest.presentation


import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class RemoteConfigUtil {

    lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = ZERO_TIME
        }
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
        }


        return remoteConfig
    }

    fun getUrl(): String = remoteConfig.getString(URL_KEY)


    companion object {
        const val URL_KEY = "url"
        private const val ZERO_TIME = 0L
    }

}