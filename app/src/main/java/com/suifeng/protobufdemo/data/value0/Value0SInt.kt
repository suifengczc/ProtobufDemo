package com.suifeng.protobufdemo.data.value0

import com.suifeng.protobufdemo.data.UnitData

/**
 * wire type == 0 时，解析sint32, sint64数据
 */
class Value0SInt : UnitData() {
    override fun parseValue() {
        parsedValue = concatValueReverse()
        parsedValue = when {
            parsedValue.length > 32 -> decode(parsedValue.toLong(2)).toString()
            else -> decode(parsedValue.toInt(2).toLong()).toString()
        }
    }

    /**
     * 解码
     */
    private fun decode(n: Long): Long {
        return n ushr 1 xor -(n and 1)
    }

    override fun toString(): String {
        return "value = $parsedValue"
    }

}