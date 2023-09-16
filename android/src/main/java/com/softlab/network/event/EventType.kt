package com.softlab.network.event

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

enum class EventType(val eventType: String) {
    CELL_INFO_CHANGED("CELL_INFO_CHANGED"),
    SIGNAL_STRENGTH_CHANGED("SIGNAL_STRENGTH_CHANGED"),
    CARRIER_NETWORK_CHANGED("CARRIER_NETWORK_CHANGED"), // From API 31
    DISPLAY_INFO_CHANGED("DISPLAY_INFO_CHANGED"),
    DATA_CONNECTION_STATE_CHANGED("DATA_CONNECTION_STATE_CHANGED"),
    DATA_ACTIVITY("DATA_ACTIVITY"),
    //DATA_ACTIVATION_STATE_CHANGED("DATA_ACTIVATION_STATE_CHANGED"),
    //CELL_LOCATION_CHANGED("CELL_LOCATION_CHANGED"),
    CALL_STATE_CHANGED("CALL_STATE_CHANGED"), // TODO
    CALL_FORWARDING_INDICATOR("CALL_FORWARDING_INDICATOR"),
    CALL_DISCONNECT_CAUSE_CHANGED("CALL_DISCONNECT_CAUSE_CHANGED"),
    //BARRING_INFO("BARRING_INFO"),
    ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED("ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED"),
    USER_MOBILE_DATA_STATE_CHANGED("USER_MOBILE_DATA_STATE_CHANGED");

    companion object {
        fun eventNames(): WritableMap {
            val events = Arguments.createMap()
            for (event in EventType.values()) {
                events.putString(event.name, event.name)
            }
            return events
        }

        fun get(eventType: String): EventType {
            return EventType.valueOf(eventType)
        }
    }
}