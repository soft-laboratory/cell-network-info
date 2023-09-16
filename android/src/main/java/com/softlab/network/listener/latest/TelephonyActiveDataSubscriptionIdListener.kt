package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyActiveDataSubscriptionIdListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.ActiveDataSubscriptionIdListener {

    override fun onActiveDataSubscriptionIdChanged(subId: Int) {
        val result: WritableMap = Arguments.createMap()
        result.putInt("subId", subId)
        eventEmitter.sendEvent(EventType.ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED, result)
    }
}