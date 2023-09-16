package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class CallDisconnectCauseListener(private val eventEmitter: EventEmitter) : PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onCallDisconnectCauseChanged(disconnectCause: Int, preciseDisconnectCause: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("disconnectCause", disconnectCause)
        result.putInt("preciseDisconnectCause", preciseDisconnectCause)
        eventEmitter.sendEvent(EventType.CALL_DISCONNECT_CAUSE_CHANGED, result)
    }
}