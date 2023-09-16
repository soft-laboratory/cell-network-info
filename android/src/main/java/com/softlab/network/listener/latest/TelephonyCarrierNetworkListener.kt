package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCarrierNetworkListener(private val eventEmitter: EventEmitter) : TelephonyCallback(),
    TelephonyCallback.CarrierNetworkListener {

    override fun onCarrierNetworkChange(active: Boolean) {
        val result: WritableMap = Arguments.createMap()
        result.putBoolean("active", active)
        eventEmitter.sendEvent(EventType.CARRIER_NETWORK_CHANGED, result)
    }
}