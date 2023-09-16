package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class CallStateChangedListener(private val eventEmitter: EventEmitter) : PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("state", state)
        result.putString("phoneNumber", phoneNumber)
        eventEmitter.sendEvent(EventType.CALL_STATE_CHANGED, result)
    }
}