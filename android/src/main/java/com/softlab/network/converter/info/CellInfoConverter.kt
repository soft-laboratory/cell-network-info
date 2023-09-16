package com.softlab.network.converter.info

import android.os.Build
import android.telephony.CellInfo
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.converter.identity.CellIdentityConverter
import com.softlab.network.converter.strength.SignalStrengthConverter
import kotlin.streams.toList

class CellInfoConverter {

    companion object {
        fun convert(cellInfo: MutableList<CellInfo>): List<WritableMap> {
            return cellInfo.stream().map { convert(it) }.toList()
        }

        fun convert(cellInfo: CellInfo): WritableMap {
            val cellInfoMap: WritableMap = Arguments.createMap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cellInfoMap.putInt(
                    CellInfoConstants.cellConnectionStatus,
                    cellInfo.cellConnectionStatus
                )
            }

            cellInfoMap.putBoolean(CellInfoConstants.isRegistered, cellInfo.isRegistered)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                cellInfoMap.putString(
                    CellInfoConstants.timestampMillis,
                    cellInfo.timestampMillis.toString()
                )

                val signalStrength = SignalStrengthConverter.convert(cellInfo.cellSignalStrength)
                cellInfoMap.putMap(CellInfoConstants.signalStrength, signalStrength)

                val cellIdentity = CellIdentityConverter.convert(cellInfo.cellIdentity)
                cellInfoMap.putMap(CellInfoConstants.cellIdentity, cellIdentity)
            }

            return cellInfoMap
        }
    }
}