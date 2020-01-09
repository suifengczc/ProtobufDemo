package com.suifeng.protobufdemo.data

/**
 * 每个字节数据
 */
class BitData(byte: Byte) {
    var binaryString: String

    init {
        val binary = Integer.toBinaryString(byte.toInt() and 0xFF)
        binaryString = "0".repeat(8 - binary.length) + binary
    }

    override fun toString(): String {
        return binaryString
    }

    /**
     * 返回后7位字节二进制字符串
     */
    fun getRealValue(): String {
        return binaryString.substring(1)
    }

    /**
     * 返回全部8位字节二进制字符串
     */
    fun getTotalValue(): String {
        return binaryString
    }

    /**
     * 判断首位是不是1，首位为1表示后一个字节和当前字节属于同一个值
     */
    fun hasNext(): Boolean {
        return '1' == binaryString[0]
    }
}