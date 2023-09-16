package com.softlab.network.extension

import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeArray

fun WritableMap.putArray(key: String, value: IntArray) {
    val values = WritableNativeArray()
    value.forEach { values.pushInt(it) }
    this.putArray(key, values)
}

fun WritableMap.putArray(key: String, value: Collection<String>) {
    val values = WritableNativeArray()
    value.forEach { values.pushString(it) }
    this.putArray(key, values)
}

fun WritableMap.putArray(key: String, value: List<WritableMap>) {
    val values = WritableNativeArray()
    value.forEach { values.pushMap(it) }
    this.putArray(key, values)
}