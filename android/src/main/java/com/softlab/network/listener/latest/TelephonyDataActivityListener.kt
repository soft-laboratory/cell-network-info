package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyDataActivityListener(private val eventEmitter: EventEmitter) : TelephonyCallback(),
    TelephonyCallback.DataActivityListener {

    override fun onDataActivity(direction: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("direction", direction)
        eventEmitter.sendEvent(EventType.DATA_ACTIVITY, result)
    }
}