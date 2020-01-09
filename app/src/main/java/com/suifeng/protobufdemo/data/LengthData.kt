package com.suifeng.protobufdemo.data

/**
 * 长度数据
 */
class LengthData : UnitData() {

    override fun parseValue() {
        parsedValue = concatValue()
    }

    fun getParsedValue(): Int {
        return parsedValue.toInt(2)
    }

    override fun toString(): String {
        return "length = ${parsedValue.toInt(2)}"
    }
}