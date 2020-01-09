package com.suifeng.protobufdemo.data.value

import com.suifeng.protobufdemo.data.UnitData
import com.suifeng.protobufdemo.decoder.toRadixNum

/**
 * 默认数据块的解析类
 */
open class ValueData : UnitData() {
    override fun parseValue() {
        parsedValue = concatValueReverse()
    }

    override fun toString(): String {
        return "value = ${parsedValue.toRadixNum(2)}"
    }
}