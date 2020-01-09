package com.suifeng.protobufdemo.data.value2

import com.suifeng.protobufdemo.data.UnitData

/**
 * wiretype == 2时的String数据块
 */
class Value2StringData : UnitData() {

    override fun parseValue(){
        var parse = ""
        value.forEach {
            parse += it.getRealValue().toInt(2).toChar()
        }
        parsedValue = parse
    }

    override fun toString(): String {
        return "value = $parsedValue"
    }


}