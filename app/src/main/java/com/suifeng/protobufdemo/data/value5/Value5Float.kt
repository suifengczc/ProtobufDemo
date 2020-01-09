package com.suifeng.protobufdemo.data.value5

import com.suifeng.protobufdemo.data.value.ValueFixedData

/**
 * wire type == 5 时，解析float
 */
class Value5Float : ValueFixedData() {

    override fun toString(): String {
        return java.lang.Float.intBitsToFloat(parsedValue.toInt(2)).toString()
    }
}