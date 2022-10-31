package ersecboom.bet.kof.presentation

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "111301a7-dba1-4cf5-977f-b034d8471722"
class AdsApp : Application() {
    private lateinit var _remoteConfigUtil: RemoteConfigUtil
    val remoteConfigUtil: RemoteConfigUtil
        get() = _remoteConfigUtil

    override fun onCreate() {
        super.onCreate()
        _remoteConfigUtil = RemoteConfigUtil().apply {
            init()
        }
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}