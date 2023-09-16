package com.softlab.network.listener

import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType
import com.softlab.network.listener.latest.TelephonyActiveDataSubscriptionIdListener
import com.softlab.network.listener.latest.TelephonyCallDisconnectCauseListener
import com.softlab.network.listener.latest.TelephonyCallForwardingIndicatorListener
import com.softlab.network.listener.latest.TelephonyCallStateListener
import com.softlab.network.listener.latest.TelephonyCarrierNetworkListener
import com.softlab.network.listener.latest.TelephonyCellInfoListener
import com.softlab.network.listener.latest.TelephonyDataActivityListener
import com.softlab.network.listener.latest.TelephonyDataConnectionStateListener
import com.softlab.network.listener.latest.TelephonyDisplayInfoListener
import com.softlab.network.listener.latest.TelephonySignalStrengthsListener
import com.softlab.network.listener.latest.TelephonyUserMobileDataStateListener
import com.softlab.network.listener.legacy.CallDisconnectCauseListener
import com.softlab.network.listener.legacy.CallForwardingIndicatorListener
import com.softlab.network.listener.legacy.CellInfoListener
import com.softlab.network.listener.legacy.DataActivityListener
import com.softlab.network.listener.legacy.DataConnectionStateListener
import com.softlab.network.listener.legacy.DisplayInfoListener
import com.softlab.network.listener.legacy.SignalStrengthsListener
import com.softlab.network.listener.legacy.UserMobileDataStateListener

class TelephonyListenerFactory(private val eventEmitter: EventEmitter) {

    @RequiresApi(Build.VERSION_CODES.S)
    fun createListener(eventType: EventType): TelephonyCallback? {
        return when (eventType) {
            EventType.CELL_INFO_CHANGED -> TelephonyCellInfoListener(eventEmitter)
            EventType.SIGNAL_STRENGTH_CHANGED -> TelephonySignalStrengthsListener(eventEmitter)
            EventType.CARRIER_NETWORK_CHANGED -> TelephonyCarrierNetworkListener(eventEmitter)
            EventType.DISPLAY_INFO_CHANGED -> TelephonyDisplayInfoListener(eventEmitter)
            EventType.DATA_CONNECTION_STATE_CHANGED -> TelephonyDataConnectionStateListener(
                eventEmitter
            )

            EventType.DATA_ACTIVITY -> TelephonyDataActivityListener(eventEmitter)
            EventType.CALL_STATE_CHANGED -> TelephonyCallStateListener(eventEmitter)
            EventType.CALL_FORWARDING_INDICATOR -> TelephonyCallForwardingIndicatorListener(
                eventEmitter
            )

            EventType.CALL_DISCONNECT_CAUSE_CHANGED -> TelephonyCallDisconnectCauseListener(
                eventEmitter
            )

            EventType.ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED -> TelephonyActiveDataSubscriptionIdListener(
                eventEmitter
            )

            EventType.USER_MOBILE_DATA_STATE_CHANGED -> TelephonyUserMobileDataStateListener(
                eventEmitter
            )
            else -> null
        }
    }

    fun createLegacyListener(eventType: EventType): PhoneStateListener? {
        return when (eventType) {
            EventType.CELL_INFO_CHANGED -> CellInfoListener(eventEmitter)
            EventType.SIGNAL_STRENGTH_CHANGED -> SignalStrengthsListener(eventEmitter)
            //EventType.CARRIER_NETWORK_CHANGED -> CarrierNetworkListener(eventEmitter)
            EventType.DISPLAY_INFO_CHANGED -> DisplayInfoListener(eventEmitter)
            EventType.DATA_CONNECTION_STATE_CHANGED -> DataConnectionStateListener(eventEmitter)
            EventType.DATA_ACTIVITY -> DataActivityListener(eventEmitter)
            //EventType.CALL_STATE_CHANGED -> CallStateListener(eventEmitter)
            EventType.CALL_FORWARDING_INDICATOR -> CallForwardingIndicatorListener(eventEmitter)
            EventType.CALL_DISCONNECT_CAUSE_CHANGED -> CallDisconnectCauseListener(eventEmitter)
            //EventType.ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED -> ActiveDataSubscriptionIdListener(eventEmitter)
            EventType.USER_MOBILE_DATA_STATE_CHANGED -> UserMobileDataStateListener(eventEmitter)
            else -> null
        }
    }
}