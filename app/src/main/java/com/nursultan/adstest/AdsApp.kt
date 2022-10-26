package com.nursultan.adstest

import android.app.Application

class AdsApp : Application() {
    private lateinit var _remoteConfigUtil: RemoteConfigUtil
    val remoteConfigUtil: RemoteConfigUtil
        get() = _remoteConfigUtil

    override fun onCreate() {
        super.onCreate()
        _remoteConfigUtil = RemoteConfigUtil().apply {
            init()
        }
    }
}