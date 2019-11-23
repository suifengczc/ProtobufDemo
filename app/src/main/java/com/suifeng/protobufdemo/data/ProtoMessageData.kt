package com.suifeng.protobufdemo.data

import com.suifeng.protobufdemo.decoder.toRadixInt

class ProtoMessageData(
    val wireType: Int,
    val fieldNum: Int,
    val length: Int,
    val value: MutableList<String>
) {

    override fun toString(): String {
        return "wireType = $wireType , fieldNum = $fieldNum , length = $length , value = ${parseData()}"
    }

    fun parseData(): String {
        var parse = ""
        when (wireType) {
            0, 1, 5 -> {
                for (i in value.size - 1 downTo 0) {
                    parse += value[i].substring(1)
                }
                parse = parse.toRadixInt(2).toString()
            }
            2 -> {
                for (i in value.indices) {
                    parse += value[i].substring(1).toRadixInt(2).toChar()
                }
            }
            else -> {
            }
        }
        return parse
    }
}