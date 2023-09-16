package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.SignalStrength
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.softlab.network.converter.strength.SignalStrengthConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonySignalStrengthsListener(private val eventEmitter: EventEmitter) :
    TelephonyCallback(), TelephonyCallback.SignalStrengthsListener {

    override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
        val result = SignalStrengthConverter.convert(signalStrength)
        eventEmitter.sendEvent(EventType.SIGNAL_STRENGTH_CHANGED, result)
    }
}