package com.softlab.network.converter.strength

import android.os.Build
import android.telephony.CellSignalStrength
import android.telephony.CellSignalStrengthCdma
import android.telephony.CellSignalStrengthGsm
import android.telephony.CellSignalStrengthLte
import android.telephony.CellSignalStrengthNr
import android.telephony.CellSignalStrengthTdscdma
import android.telephony.CellSignalStrengthWcdma
import android.telephony.SignalStrength
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeArray
import com.softlab.network.extension.putArray
import java.lang.RuntimeException
import kotlin.streams.toList

class SignalStrengthConverter {

    companion object {
        fun convert(signalStrength: SignalStrength): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            signalStrengthMap.putInt(SignalStrengthConstants.level, signalStrength.level)
            signalStrengthMap.putString(
                SignalStrengthConstants.timestampMillis,
                signalStrength.timestampMillis.toString()
            )
            val cellSignalStrengths =
                signalStrength.cellSignalStrengths.stream().map { convert(it) }.toList()
            signalStrengthMap.putArray(
                SignalStrengthConstants.cellSignalStrengths,
                cellSignalStrengths
            )

            return signalStrengthMap
        }

        fun convert(signalStrength: CellSignalStrength): WritableMap {
            return when {
                signalStrength is CellSignalStrengthGsm -> convertSignalStrength(signalStrength)
                signalStrength is CellSignalStrengthWcdma -> convertSignalStrength(signalStrength)
                signalStrength is CellSignalStrengthLte -> convertSignalStrength(signalStrength)
                signalStrength is CellSignalStrengthCdma -> convertSignalStrength(signalStrength)
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                    return when (signalStrength) {
                        is CellSignalStrengthTdscdma -> convertSignalStrength(signalStrength)
                        is CellSignalStrengthNr -> convertSignalStrength(signalStrength)
                        else -> throw RuntimeException("Can not convert signal strength")
                    }
                }

                else -> throw RuntimeException("Can not convert signal strength")
            }
        }

        private fun convertSignalStrength(signalStrength: CellSignalStrengthGsm): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)
            signalStrengthMap.putInt(
                SignalStrengthConstants.bitErrorRate,
                signalStrength.bitErrorRate
            )
            signalStrengthMap.putInt(SignalStrengthConstants.rssi, signalStrength.rssi)
            signalStrengthMap.putInt(
                SignalStrengthConstants.timingAdvance,
                signalStrength.timingAdvance
            )

            return signalStrengthMap
        }

        private fun convertSignalStrength(signalStrength: CellSignalStrengthWcdma): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)
            signalStrengthMap.putInt(SignalStrengthConstants.ecNo, signalStrength.ecNo)

            return signalStrengthMap
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun convertSignalStrength(signalStrength: CellSignalStrengthTdscdma): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)
            signalStrengthMap.putInt(SignalStrengthConstants.rscp, signalStrength.rscp)

            return signalStrengthMap
        }

        private fun convertSignalStrength(signalStrength: CellSignalStrengthLte): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)

            signalStrengthMap.putInt(SignalStrengthConstants.cqi, signalStrength.cqi)
            signalStrengthMap.putInt(
                SignalStrengthConstants.cqiTableIndex,
                signalStrength.cqiTableIndex
            )
            signalStrengthMap.putInt(SignalStrengthConstants.rsrp, signalStrength.rsrp)
            signalStrengthMap.putInt(SignalStrengthConstants.rsrq, signalStrength.rsrq)
            signalStrengthMap.putInt(SignalStrengthConstants.rssi, signalStrength.rssi)
            signalStrengthMap.putInt(SignalStrengthConstants.rssnr, signalStrength.rssnr)
            signalStrengthMap.putInt(
                SignalStrengthConstants.timingAdvance,
                signalStrength.timingAdvance
            )

            return signalStrengthMap
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun convertSignalStrength(signalStrength: CellSignalStrengthNr): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)

            val csiCqiReport = WritableNativeArray()
            signalStrength.csiCqiReport.forEach { csiCqiReport.pushInt(it) }
            signalStrengthMap.putArray(SignalStrengthConstants.csiCqiReport, csiCqiReport)
            signalStrengthMap.putInt(
                SignalStrengthConstants.csiCqiTableIndex,
                signalStrength.csiCqiTableIndex
            )
            signalStrengthMap.putInt(SignalStrengthConstants.csiRsrp, signalStrength.csiRsrp)
            signalStrengthMap.putInt(SignalStrengthConstants.csiRsrq, signalStrength.csiRsrq)
            signalStrengthMap.putInt(SignalStrengthConstants.csiSinr, signalStrength.csiSinr)
            signalStrengthMap.putInt(SignalStrengthConstants.ssRsrp, signalStrength.ssRsrp)
            signalStrengthMap.putInt(SignalStrengthConstants.ssRsrq, signalStrength.ssRsrq)
            signalStrengthMap.putInt(SignalStrengthConstants.ssSinr, signalStrength.ssSinr)

            return signalStrengthMap
        }

        private fun convertSignalStrength(signalStrength: CellSignalStrengthCdma): WritableMap {
            val signalStrengthMap: WritableMap = Arguments.createMap()
            processSignalStrength(signalStrength, signalStrengthMap)

            signalStrengthMap.putInt(SignalStrengthConstants.cdmaDbm, signalStrength.cdmaDbm)
            signalStrengthMap.putInt(SignalStrengthConstants.cdmaEcio, signalStrength.cdmaEcio)
            signalStrengthMap.putInt(SignalStrengthConstants.cdmaLevel, signalStrength.cdmaLevel)
            signalStrengthMap.putInt(SignalStrengthConstants.evdoDbm, signalStrength.evdoDbm)
            signalStrengthMap.putInt(SignalStrengthConstants.evdoEcio, signalStrength.evdoEcio)
            signalStrengthMap.putInt(SignalStrengthConstants.evdoLevel, signalStrength.evdoLevel)
            signalStrengthMap.putInt(SignalStrengthConstants.evdoSnr, signalStrength.evdoSnr)

            return signalStrengthMap
        }

        private fun processSignalStrength(
            cellSignalStrength: CellSignalStrength,
            mapCellSignalStrength: WritableMap
        ) {
            mapCellSignalStrength.putInt(
                SignalStrengthConstants.asuLevel,
                cellSignalStrength.asuLevel
            )
            mapCellSignalStrength.putInt(SignalStrengthConstants.dBm, cellSignalStrength.dbm)
            mapCellSignalStrength.putInt(SignalStrengthConstants.level, cellSignalStrength.level)
        }
    }
}
