package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.TelephonyCallback
import android.telephony.TelephonyDisplayInfo
import androidx.annotation.RequiresApi
import com.softlab.network.converter.display.DisplayInfoConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyDisplayInfoListener(private val eventEmitter: EventEmitter) : TelephonyCallback(),
    TelephonyCallback.DisplayInfoListener {

    override fun onDisplayInfoChanged(telephonyDisplayInfo: TelephonyDisplayInfo) {
        val result = DisplayInfoConverter.convert(telephonyDisplayInfo)
        eventEmitter.sendEvent(EventType.DISPLAY_INFO_CHANGED, result)
    }
}