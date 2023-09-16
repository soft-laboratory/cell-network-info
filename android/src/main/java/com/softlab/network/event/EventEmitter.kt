package com.softlab.network.event

import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.softlab.network.RNNetworkInfoModule.Companion.TAG

class EventEmitter(private val reactApplicationContext: ReactApplicationContext) {
    /**
     * Sends a [EventType] to the React Native JS module
     * [com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter].
     *
     *
     * Currently having no active [com.facebook.react.bridge.CatalystInstance] will not cause
     * the application to crash, although I'm not sure if it should.
     *
     * @param event the [EventType] being sent
     * @param body the content of the event
     */
    fun sendEvent(event: EventType, body: Any) {
        val context: ReactContext = reactApplicationContext
        if (context.hasActiveReactInstance()) {
            context
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit(event.eventType, body)
        } else {
            Log.e(TAG, "There is currently no active Catalyst instance")
        }
    }
}