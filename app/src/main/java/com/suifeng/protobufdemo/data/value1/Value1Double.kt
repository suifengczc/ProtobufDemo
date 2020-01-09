package com.suifeng.protobufdemo.data.value1

import com.suifeng.protobufdemo.data.value.ValueFixedData

/**
 * wire type == 1 时，解析double
 */
class Value1Double : ValueFixedData() {


    override fun toString(): String {
        return java.lang.Double.longBitsToDouble(parsedValue.toLong(2)).toString()
    }
}