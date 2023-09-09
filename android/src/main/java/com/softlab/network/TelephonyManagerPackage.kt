package com.softlab.network;

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class TelephonyManagerPackage : TurboReactPackage() {
    override fun getModule(name: String?, reactContext: ReactApplicationContext): NativeModule? =
    if (name == TelephonyManagerModule.NAME) {
        TelephonyManagerModule(reactContext)
    } else {
        null
    }
    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf(
            TelephonyManagerModule.NAME to ReactModuleInfo(
                TelephonyManagerModule.NAME,
                TelephonyManagerModule.NAME,
                false, // canOverrideExistingModule
                false, // needsEagerInit
                true, // hasConstants
                false, // isCxxModule
                true // isTurboModule
            )
        )
    }
}
