package com.suifeng.protobufdemo.data

class ProtoMessageData(
    var wireType: Int,
    var fieldNum: Int,
    var length: Int,
    value: MutableList<BitData>
) : UnitData() {

    override fun parseValue(): String {
        var parse = ""
        when (wireType) {
            0, 1, 5 -> {
                parse = concatValueReverse()
                parse = when {
                    parse.length > 32 -> parse.toLong(2).toString()
                    else -> parse.toInt(2).toString()
                }
            }
            2 -> {
                parse = concatValue()
            }
            else -> {

            }
        }
        return parse
    }

    override fun toString(): String {
        return "wireType = $wireType , fieldNum = $fieldNum , length = $length , value = ${parseValue()}"
    }


}