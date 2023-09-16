package com.softlab.network.event

import android.os.Build
import android.telephony.PhoneStateListener

class EventMapper {
    companion object {
        fun map(eventType: EventType): Int? {
            return when (eventType) {
                EventType.SIGNAL_STRENGTH_CHANGED -> PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                EventType.CELL_INFO_CHANGED -> PhoneStateListener.LISTEN_CELL_INFO
                EventType.DISPLAY_INFO_CHANGED -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    PhoneStateListener.LISTEN_DISPLAY_INFO_CHANGED
                } else {
                    null
                }

                EventType.DATA_CONNECTION_STATE_CHANGED -> PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
                EventType.DATA_ACTIVITY -> PhoneStateListener.LISTEN_DATA_ACTIVITY
                EventType.CALL_STATE_CHANGED -> PhoneStateListener.LISTEN_CALL_STATE
                EventType.CALL_FORWARDING_INDICATOR -> PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                EventType.CALL_DISCONNECT_CAUSE_CHANGED -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    PhoneStateListener.LISTEN_CALL_DISCONNECT_CAUSES
                } else {
                    null
                }

                EventType.ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    PhoneStateListener.LISTEN_ACTIVE_DATA_SUBSCRIPTION_ID_CHANGE
                } else {
                    null
                }

                EventType.USER_MOBILE_DATA_STATE_CHANGED -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    PhoneStateListener.LISTEN_USER_MOBILE_DATA_STATE
                } else {
                    null
                }

                else -> throw RuntimeException("Can not map event type $eventType")
            }
        }
    }
}