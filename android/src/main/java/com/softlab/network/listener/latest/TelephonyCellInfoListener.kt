package com.softlab.network.listener.latest

import android.os.Build
import android.telephony.CellInfo
import android.telephony.TelephonyCallback
import androidx.annotation.RequiresApi
import com.softlab.network.converter.info.CellInfoConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

@RequiresApi(Build.VERSION_CODES.S)
class TelephonyCellInfoListener(private val eventEmitter: EventEmitter) : TelephonyCallback(),
    TelephonyCallback.CellInfoListener {

    override fun onCellInfoChanged(activeCellInfo: MutableList<CellInfo>) {
        val result = CellInfoConverter.convert(activeCellInfo)
        eventEmitter.sendEvent(EventType.CELL_INFO_CHANGED, result)
    }
}