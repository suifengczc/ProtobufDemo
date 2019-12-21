package com.suifeng.protobufdemo.data

/**
 * sint32数据块
 */
class Value1SInt : UnitData() {
    override fun parseValue(): String {
        parsedValue = concatValueReverse()
        parsedValue = decode(parsedValue.toInt(2)).toString()
        return parsedValue
    }

    /**
     * 解码
     */
    private fun decode(n: Int): Int {
        return n ushr 1 xor -(n and 1)
    }

    override fun toString(): String {
        return "value = $parsedValue"
    }

}