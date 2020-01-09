package com.suifeng.protobufdemo.data.value

import com.suifeng.protobufdemo.decoder.toRadixNum

/**
 * wire type == 1 || 5 时，解析fixed64, sfixed64，fixed32, sfixed32
 */
open class ValueFixedData : ValueData() {

    override fun parseValue() {
        parsedValue = concatTotalValueReverse()
    }

    override fun toString(): String {
        return parsedValue.toRadixNum(2)
    }
}