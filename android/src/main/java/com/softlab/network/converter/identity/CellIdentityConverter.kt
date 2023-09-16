package com.softlab.network.converter.identity

import android.os.Build
import android.telephony.CellIdentity
import android.telephony.CellIdentityCdma
import android.telephony.CellIdentityGsm
import android.telephony.CellIdentityLte
import android.telephony.CellIdentityNr
import android.telephony.CellIdentityTdscdma
import android.telephony.CellIdentityWcdma
import android.telephony.ClosedSubscriberGroupInfo
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.softlab.network.extension.putArray
import java.lang.RuntimeException

class CellIdentityConverter {

    companion object {
        fun convert(cellIdentity: CellIdentity): WritableMap {
            return when {
                cellIdentity is CellIdentityGsm -> convert(cellIdentity)
                cellIdentity is CellIdentityWcdma -> convert(cellIdentity)
                cellIdentity is CellIdentityLte -> convert(cellIdentity)
                cellIdentity is CellIdentityCdma -> convert(cellIdentity)
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                    return when (cellIdentity) {
                        is CellIdentityNr -> convert(cellIdentity)
                        is CellIdentityTdscdma -> convert(cellIdentity)
                        else -> throw RuntimeException("Can not convert cell identity")
                    }
                }

                else -> throw RuntimeException("Can not convert cell identity")
            }
        }

        private fun convert(cellIdentity: CellIdentityGsm): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                processCellIdentity(cellIdentity, cellIdentityMap)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                cellIdentityMap.putArray(
                    SignalIdentityConstants.additionalPlmns,
                    cellIdentity.additionalPlmns
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cellIdentityMap.putInt(SignalIdentityConstants.arfcn, cellIdentity.arfcn)
                cellIdentityMap.putInt(SignalIdentityConstants.bsic, cellIdentity.bsic)
            }

            cellIdentityMap.putInt(SignalIdentityConstants.cid, cellIdentity.cid)
            cellIdentityMap.putInt(SignalIdentityConstants.lac, cellIdentity.lac)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cellIdentityMap.putString(SignalIdentityConstants.mcc, cellIdentity.mccString)
                cellIdentityMap.putString(SignalIdentityConstants.mnc, cellIdentity.mncString)
                cellIdentityMap.putString(
                    SignalIdentityConstants.mobileNetworkOperator,
                    cellIdentity.mobileNetworkOperator
                )
            }

            return cellIdentityMap
        }

        private fun convert(cellIdentity: CellIdentityCdma): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                processCellIdentity(cellIdentity, cellIdentityMap)
            }

            cellIdentityMap.putInt(
                SignalIdentityConstants.basestationId,
                cellIdentity.basestationId
            )
            cellIdentityMap.putInt(SignalIdentityConstants.latitude, cellIdentity.latitude)
            cellIdentityMap.putInt(SignalIdentityConstants.longitude, cellIdentity.longitude)
            cellIdentityMap.putInt(SignalIdentityConstants.networkId, cellIdentity.networkId)
            cellIdentityMap.putInt(SignalIdentityConstants.systemId, cellIdentity.systemId)

            return cellIdentityMap
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun convert(cellIdentity: CellIdentityNr): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            processCellIdentity(cellIdentity, cellIdentityMap)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                cellIdentityMap.putArray(
                    SignalIdentityConstants.additionalPlmns,
                    cellIdentity.additionalPlmns
                )
                cellIdentityMap.putArray(SignalIdentityConstants.bands, cellIdentity.bands)
            }
            cellIdentityMap.putString(SignalIdentityConstants.mcc, cellIdentity.mccString)
            cellIdentityMap.putString(SignalIdentityConstants.mnc, cellIdentity.mncString)
            cellIdentityMap.putDouble(SignalIdentityConstants.nci, cellIdentity.nci.toDouble())
            cellIdentityMap.putInt(SignalIdentityConstants.nrarfcn, cellIdentity.nrarfcn)
            cellIdentityMap.putInt(SignalIdentityConstants.pci, cellIdentity.pci)
            cellIdentityMap.putInt(SignalIdentityConstants.tac, cellIdentity.tac)

            return cellIdentityMap
        }

        private fun convert(cellIdentity: CellIdentityLte): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                processCellIdentity(cellIdentity, cellIdentityMap)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                cellIdentityMap.putArray(
                    SignalIdentityConstants.additionalPlmns,
                    cellIdentity.additionalPlmns
                )
                cellIdentityMap.putMap(
                    SignalIdentityConstants.closedSubscriberGroupInfo,
                    convert(cellIdentity.closedSubscriberGroupInfo)
                )
                cellIdentityMap.putArray(SignalIdentityConstants.bands, cellIdentity.bands)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cellIdentityMap.putInt(SignalIdentityConstants.bandwidth, cellIdentity.bandwidth)
                cellIdentityMap.putString(SignalIdentityConstants.mcc, cellIdentity.mccString)
                cellIdentityMap.putString(SignalIdentityConstants.mnc, cellIdentity.mncString)
                cellIdentityMap.putString(
                    SignalIdentityConstants.mobileNetworkOperator,
                    cellIdentity.mobileNetworkOperator
                )
            }
            cellIdentityMap.putInt(SignalIdentityConstants.cid, cellIdentity.ci)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cellIdentityMap.putInt(SignalIdentityConstants.earfcn, cellIdentity.earfcn)
            }
            cellIdentityMap.putInt(SignalIdentityConstants.pci, cellIdentity.pci)
            cellIdentityMap.putInt(SignalIdentityConstants.tac, cellIdentity.tac)

            return cellIdentityMap
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        private fun convert(cellIdentity: CellIdentityTdscdma): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            processCellIdentity(cellIdentity, cellIdentityMap)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                cellIdentityMap.putArray(
                    SignalIdentityConstants.additionalPlmns,
                    cellIdentity.additionalPlmns
                )
                cellIdentityMap.putMap(
                    SignalIdentityConstants.closedSubscriberGroupInfo,
                    convert(cellIdentity.closedSubscriberGroupInfo)
                )
            }
            cellIdentityMap.putInt(SignalIdentityConstants.cid, cellIdentity.cid)
            cellIdentityMap.putInt(SignalIdentityConstants.cpid, cellIdentity.cpid)
            cellIdentityMap.putInt(SignalIdentityConstants.lac, cellIdentity.lac)
            cellIdentityMap.putString(SignalIdentityConstants.mcc, cellIdentity.mccString)
            cellIdentityMap.putString(SignalIdentityConstants.mnc, cellIdentity.mncString)
            cellIdentityMap.putString(
                SignalIdentityConstants.mobileNetworkOperator,
                cellIdentity.mobileNetworkOperator
            )
            cellIdentityMap.putInt(SignalIdentityConstants.uarfcn, cellIdentity.uarfcn)

            return cellIdentityMap
        }

        private fun convert(cellIdentity: CellIdentityWcdma): WritableMap {
            val cellIdentityMap: WritableMap = Arguments.createMap()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                processCellIdentity(cellIdentity, cellIdentityMap)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                cellIdentityMap.putArray(
                    SignalIdentityConstants.additionalPlmns,
                    cellIdentity.additionalPlmns
                )
                cellIdentityMap.putMap(
                    SignalIdentityConstants.closedSubscriberGroupInfo,
                    convert(cellIdentity.closedSubscriberGroupInfo)
                )
            }

            cellIdentityMap.putInt(SignalIdentityConstants.cid, cellIdentity.cid)
            cellIdentityMap.putInt(SignalIdentityConstants.lac, cellIdentity.lac)
            cellIdentityMap.putInt(SignalIdentityConstants.psc, cellIdentity.psc)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cellIdentityMap.putString(SignalIdentityConstants.mcc, cellIdentity.mccString)
                cellIdentityMap.putString(SignalIdentityConstants.mnc, cellIdentity.mncString)
                cellIdentityMap.putString(
                    SignalIdentityConstants.mobileNetworkOperator,
                    cellIdentity.mobileNetworkOperator
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cellIdentityMap.putInt(SignalIdentityConstants.uarfcn, cellIdentity.uarfcn)
            }

            return cellIdentityMap
        }

        @RequiresApi(Build.VERSION_CODES.P)
        private fun processCellIdentity(
            cellIdentity: CellIdentity,
            cellIdentityMap: WritableMap
        ) {
            cellIdentityMap.putString(
                SignalIdentityConstants.operatorAlphaLong,
                cellIdentity.operatorAlphaLong?.toString()
            )
            cellIdentityMap.putString(
                SignalIdentityConstants.operatorAlphaShort,
                cellIdentity.operatorAlphaShort?.toString()
            )
        }

        @RequiresApi(Build.VERSION_CODES.R)
        private fun convert(closedSubscriberGroupInfo: ClosedSubscriberGroupInfo?): WritableMap? {
            val result: WritableMap = Arguments.createMap()

            if (closedSubscriberGroupInfo == null) {
                return null
            }

            result.putString("homeNodebName", closedSubscriberGroupInfo.homeNodebName)
            result.putInt("csgIdentity", closedSubscriberGroupInfo.csgIdentity)
            result.putBoolean("csgIndicator", closedSubscriberGroupInfo.csgIndicator)
            return result
        }
    }
}
