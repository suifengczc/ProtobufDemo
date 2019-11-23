package com.suifeng.protobufdemo.data

class BitData(byte: Byte) {
    var binaryString: String

    init {
        val binary = Integer.toBinaryString(byte.toInt() and 0xFF)
        binaryString = "0".repeat(8 - binary.length) + binary
    }

    override fun toString(): String {
        return binaryString
    }

    fun hasNext(): Boolean {
        return '1' == binaryString[0]
    }
}