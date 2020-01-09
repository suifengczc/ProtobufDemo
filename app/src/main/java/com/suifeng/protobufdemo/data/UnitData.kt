package com.suifeng.protobufdemo.data

/**
 * 一个数据块内容
 */
abstract class UnitData {
    var value: MutableList<BitData> = mutableListOf()
    var parsedValue = ""

    /**
     * 添加一个bitData
     */
    fun addValue(bitData: BitData) {
        value.add(bitData)
    }

    /**
     * 解析当前UnitData数据
     */
    abstract fun parseValue()

    /**
     * 拼接value的真实值
     */
    fun concatValue(): String {
        var parse = ""
        for (i in value.indices) {
            parse += value[i].getRealValue()
        }
        return parse
    }

    /**
     * 反向拼接value的真实值
     */
    fun concatValueReverse(): String {
        var parse = ""
        for (i in value.size - 1 downTo 0) {
            parse += value[i].getRealValue()
        }
        return parse
    }

    /**
     * 反向拼接value的全部值
     */
    open fun concatTotalValueReverse(): String {
        var parse = ""
        for (i in value.size - 1 downTo 0) {
            parse += value[i].getTotalValue()
        }
        return parse
    }

}