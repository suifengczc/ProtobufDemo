package com.suifeng.protobufdemo.data

/**
 * 类型数据，包含wireType和id
 */
class TypeData : UnitData() {

    override fun parseValue() {
        parsedValue = concatValueReverse()
    }

    /**
     * 返回wireType二进制字符串
     */
    fun getWireType(): String {
        return parsedValue.substring(parsedValue.length - 3)
    }

    /**
     * 返回id二进制字符串
     */
    fun getID(): String {
        return parsedValue.substring(0, parsedValue.length - 3)
    }

    override fun toString(): String {
        return "wireType = ${getWireType().toInt(2)} , ID = ${getID().toInt(2)}"
    }
}