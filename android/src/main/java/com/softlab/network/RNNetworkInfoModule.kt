package com.softlab.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventMapper
import com.softlab.network.event.EventType
import com.softlab.network.listener.TelephonyListenerFactory
import com.softlab.network.listener.legacy.CallForwardingIndicatorListener
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger


class RNNetworkInfoModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private val telephonyManager: TelephonyManager =
        reactContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    private val connectivityManager =
        reactContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val eventEmitter = EventEmitter(reactApplicationContext)
    private val listenerCounts: MutableMap<String, AtomicInteger> = ConcurrentHashMap()
    private val listeners: MutableMap<String, TelephonyCallback> = ConcurrentHashMap()
    private val legacyListeners: MutableMap<String, PhoneStateListener> = ConcurrentHashMap()
    private val listenerFactory = TelephonyListenerFactory(eventEmitter)

    override fun getName(): String {
        return NAME
    }

/*    @ReactMethod
    fun isWifi(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        if (activeNetwork != null) {
            val actNw = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            if (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            }
        }

        return false
    }*/

/*    fun getDataNetworkType(): Int {
        return telephonyManager.dataNetworkType
    }

    fun getVoiceNetworkType(): Int {
        return telephonyManager.voiceNetworkType
    }

    fun getPhoneType(): Int {
        return telephonyManager.phoneType
    }

    fun getActiveModemCount(): Int {
        return telephonyManager.activeModemCount
    }*/

    @ReactMethod
    fun addListener(requestedEvent: String) {
        if (!EventType.eventNames().hasKey(requestedEvent)) {
            throw InvalidNetworkInfoEventException(requestedEvent)
        }

        val listenerCount: AtomicInteger = listenerCounts[requestedEvent] ?: AtomicInteger(0)
        if (!listenerCounts.containsKey(requestedEvent)) {
            listenerCounts[requestedEvent] = listenerCount
        }

        val currentCount = listenerCount.incrementAndGet()

        if (currentCount == 1 &&
            ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !listeners.containsKey(
                requestedEvent
            )) ||
                    (Build.VERSION.SDK_INT < Build.VERSION_CODES.S && !legacyListeners.containsKey(
                        requestedEvent
                    )))
        ) {
            startListener(EventType.get(requestedEvent))
        }

        Log.d(
            TAG,
            String.format(
                "Adding listener to %s, currently have %d listeners",
                requestedEvent,
                currentCount
            )
        )
    }

    @ReactMethod
    fun removeListener(requestedEvent: String) {
        if (!EventType.eventNames().hasKey(requestedEvent)) {
            return
        }

        val listenerCount: AtomicInteger? = listenerCounts[requestedEvent]
        if (listenerCount == null || listenerCount.get() == 0) {
            return
        }

        val currentCount = listenerCount.decrementAndGet()

        if (currentCount == 0) {
            stopListener(requestedEvent)
        }

        Log.d(
            TAG,
            String.format(
                "Removing listener to %s, currently have %d listeners",
                requestedEvent,
                currentCount
            )
        )
    }

    @ReactMethod
    fun removeAllListeners(requestedEvent: String) {
        if (!EventType.eventNames().hasKey(requestedEvent)) {
            return
        }

        val listenerCount: AtomicInteger? = listenerCounts[requestedEvent]
        listenerCount?.set(0)
        stopListener(requestedEvent)
        Log.d(
            TAG,
            String.format("Removing listener to %s, currently have %d listeners", requestedEvent, 0)
        )
    }

    private fun startListener(eventType: EventType) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val telephonyListener = listenerFactory.createListener(eventType) ?: return
            listeners[eventType.eventType] = telephonyListener
            telephonyManager.registerTelephonyCallback(reactContext.mainExecutor, telephonyListener)
        } else {
            val eventId = EventMapper.map(eventType) ?: return
            val telephonyListener = listenerFactory.createLegacyListener(eventType) ?: return
            legacyListeners[eventType.eventType] = telephonyListener
            telephonyManager.listen(telephonyListener, eventId)
        }
    }

    private fun stopListener(eventType: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val listener = listeners[eventType]
            if (listener != null) {
                telephonyManager.unregisterTelephonyCallback(listener)
            }
        } else {
            val listener = legacyListeners[eventType]
            if (listener != null) {
                telephonyManager.listen(listener, PhoneStateListener.LISTEN_NONE)
            }
        }
    }

    companion object {
        const val NAME = "RNNetworkInfo"
        const val TAG = "RNNetworkInfoModule"
    }
}
