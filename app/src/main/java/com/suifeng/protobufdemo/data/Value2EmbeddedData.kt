package com.suifeng.protobufdemo.data

import com.suifeng.protobufdemo.decoder.ProtoDecoder

/**
 * value数据中wireType == 2 时的message嵌套数据块
 */
class Value2EmbeddedData : UnitData() {
    private var dataGroup = DataGroup()
    override fun parseValue(): String {
        dataGroup = ProtoDecoder(value).decode()
        parsedValue = dataGroup.toString()
        return parsedValue
    }

    override fun toString(): String {
        return parsedValue
    }

}