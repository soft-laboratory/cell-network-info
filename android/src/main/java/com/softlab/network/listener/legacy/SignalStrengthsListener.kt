package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import com.softlab.network.converter.strength.SignalStrengthConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class SignalStrengthsListener(private val eventEmitter: EventEmitter) :
    PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
        if (signalStrength != null) {
            val signalStrengthResult = SignalStrengthConverter.convert(signalStrength)
            eventEmitter.sendEvent(EventType.SIGNAL_STRENGTH_CHANGED, signalStrengthResult)
        }
    }
}