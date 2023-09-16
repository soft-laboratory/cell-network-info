package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCallStateListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.CallStateListener {

    override fun onCallStateChanged(state: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("state", state)
        eventEmitter.sendEvent(EventType.CALL_STATE_CHANGED, result)
    }
}