package com.softlab.network.listener.legacy

import android.telephony.CellInfo
import android.telephony.PhoneStateListener
import com.softlab.network.converter.info.CellInfoConverter
import com.softlab.network.event.EventEmitter
import com.softlab.network.event.EventType

class CellInfoListener(private val eventEmitter: EventEmitter): PhoneStateListener() {

    @Deprecated("Deprecated in Java")
    override fun onCellInfoChanged(cellInfo: MutableList<CellInfo>?) {
        if (cellInfo != null) {
            val cellInfoResult = CellInfoConverter.convert(cellInfo)
            eventEmitter.sendEvent(EventType.CELL_INFO_CHANGED, cellInfoResult)
        }
    }
}