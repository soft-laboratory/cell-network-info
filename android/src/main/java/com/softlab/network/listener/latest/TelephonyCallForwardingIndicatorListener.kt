package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCallForwardingIndicatorListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.CallForwardingIndicatorListener {

    override fun onCallForwardingIndicatorChanged(cfi: Boolean) {
        val result: WritableMap = Arguments.createMap()
        result.putBoolean("cfi", cfi)
        eventEmitter.sendEvent(EventType.CALL_FORWARDING_INDICATOR, result)
    }
}