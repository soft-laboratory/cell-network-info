package com.softlab.network.listener.legacy

import android.telephony.PhoneStateListener
import android.telephony.TelephonyDisplayInfo
import com.softlab.network.converter.display.DisplayInfoConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class DisplayInfoListener(private val eventEmitter: EventEmitter) : PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onDisplayInfoChanged(telephonyDisplayInfo: TelephonyDisplayInfo) {
        val result = DisplayInfoConverter.convert(telephonyDisplayInfo)
        eventEmitter.sendEvent(EventType.DISPLAY_INFO_CHANGED, result)
    }
}