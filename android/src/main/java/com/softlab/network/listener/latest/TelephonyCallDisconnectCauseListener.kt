package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCallDisconnectCauseListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.CallDisconnectCauseListener {

    override fun onCallDisconnectCauseChanged(disconnectCause: Int, preciseDisconnectCause: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("disconnectCause", disconnectCause)
        result.putInt("preciseDisconnectCause", preciseDisconnectCause)
        eventEmitter.sendEvent(EventType.CALL_DISCONNECT_CAUSE_CHANGED, result)
    }
}