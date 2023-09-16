package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyDataConnectionStateListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.DataConnectionStateListener {

    override fun onDataConnectionStateChanged(state: Int, networkType: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("state", state)
        result.putInt("networkType", networkType)
        eventEmitter.sendEvent(EventType.DATA_CONNECTION_STATE_CHANGED, result)
    }
}