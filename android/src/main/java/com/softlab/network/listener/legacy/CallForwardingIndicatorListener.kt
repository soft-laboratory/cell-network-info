package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class CallForwardingIndicatorListener(private val eventEmitter: EventEmitter) : PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onCallForwardingIndicatorChanged(cfi: Boolean) {
        val result: WritableMap = Arguments.createMap()
        result.putBoolean("cfi", cfi)
        eventEmitter.sendEvent(EventType.CALL_FORWARDING_INDICATOR, result)
    }
}