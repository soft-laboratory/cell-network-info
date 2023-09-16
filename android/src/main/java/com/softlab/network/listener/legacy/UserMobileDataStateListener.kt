package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class UserMobileDataStateListener(private val eventEmitter: EventEmitter) :
    PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onUserMobileDataStateChanged(enabled: Boolean) {
        val result: WritableMap = Arguments.createMap()
        result.putBoolean("enabled", enabled)
        eventEmitter.sendEvent(EventType.USER_MOBILE_DATA_STATE_CHANGED, result)
    }
}