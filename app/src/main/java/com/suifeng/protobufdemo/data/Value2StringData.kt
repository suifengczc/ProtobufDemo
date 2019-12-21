package com.suifeng.protobufdemo.data

/**
 * wiretype == 2时的String数据块
 */
class Value2StringData : UnitData() {

    override fun parseValue(): String {
        var parse = ""
        value.forEach {
            parse += it.getRealValue().toInt(2).toChar()
        }
        parsedValue = parse
        return parsedValue
    }

    override fun toString(): String {
        return "value = $parsedValue"
    }


}