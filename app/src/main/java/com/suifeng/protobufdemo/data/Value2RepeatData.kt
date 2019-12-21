package com.suifeng.protobufdemo.data

class Value2RepeatData : UnitData() {
    override fun parseValue(): String {
        parsedValue = concatValueReverse()
        return parsedValue
    }

    override fun toString(): String {
        return "value = ${parsedValue.toInt(2)}"
    }

}