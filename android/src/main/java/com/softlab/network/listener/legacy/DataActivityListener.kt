package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class DataActivityListener(private val eventEmitter: EventEmitter) : PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onDataActivity(direction: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("direction", direction)
        eventEmitter.sendEvent(EventType.DATA_ACTIVITY, result)
    }
}