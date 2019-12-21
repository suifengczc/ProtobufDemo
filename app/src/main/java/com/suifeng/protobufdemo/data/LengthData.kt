package com.suifeng.protobufdemo.data

/**
 * 长度数据
 */
class LengthData : UnitData() {

    override fun parseValue(): String {
        return concatValue()
    }

    override fun toString(): String {
        return "length = ${concatValue().toInt(2)}"
    }
}