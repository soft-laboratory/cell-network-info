package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class DataConnectionStateListener(private val eventEmitter: EventEmitter) :
    PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onDataConnectionStateChanged(state: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("state", state)
        eventEmitter.sendEvent(EventType.DATA_CONNECTION_STATE_CHANGED, result)
    }
}