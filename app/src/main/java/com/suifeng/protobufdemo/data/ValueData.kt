package com.suifeng.protobufdemo.data

import com.suifeng.protobufdemo.decoder.toRadixNum

/**
 * 数据块
 */
open class ValueData : UnitData() {
    override fun parseValue(): String {
        parsedValue = concatValueReverse()
        return parsedValue
    }

    override fun toString(): String {
//        val v = when {
//            parsedValue.length > 32 -> {
//                parsedValue.toLong(2).toString()
//            }
//            else -> {
//                parsedValue.toInt(2).toString()
//            }
//        }
        return "value = ${parsedValue.toRadixNum(2)}"
    }
}