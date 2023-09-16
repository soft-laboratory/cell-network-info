package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyUserMobileDataStateListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.UserMobileDataStateListener {

    override fun onUserMobileDataStateChanged(enabled: Boolean) {
        val result: WritableMap = Arguments.createMap()
        result.putBoolean("enabled", enabled)
        eventEmitter.sendEvent(EventType.USER_MOBILE_DATA_STATE_CHANGED, result)
    }
}