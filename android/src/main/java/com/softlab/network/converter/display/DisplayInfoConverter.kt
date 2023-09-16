package com.softlab.network.converter.display

import android.os.Build
import android.telephony.TelephonyDisplayInfo
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

@RequiresApi(Build.VERSION_CODES.R)
class DisplayInfoConverter {

    companion object {
        fun convert(telephonyDisplayInfo: TelephonyDisplayInfo): WritableMap {
            val telephonyDisplayInfoMap: WritableMap = Arguments.createMap()
            telephonyDisplayInfoMap.putInt(
                DisplayInfoConstants.networkType,
                telephonyDisplayInfo.networkType
            )
            telephonyDisplayInfoMap.putInt(
                DisplayInfoConstants.overrideNetworkType,
                telephonyDisplayInfo.overrideNetworkType
            )

            return telephonyDisplayInfoMap
        }
    }
}