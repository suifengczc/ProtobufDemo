package com.suifeng.protobufdemo.data.value2

import com.suifeng.protobufdemo.data.DataGroup
import com.suifeng.protobufdemo.data.UnitData
import com.suifeng.protobufdemo.decoder.ProtoDecoder

/**
 * wireType == 2 时，解析embedded messages
 */
class Value2EmbeddedData : UnitData() {
    private var dataGroup = DataGroup()
    override fun parseValue() {
        //因为是嵌套的数据，所以用ProtoDecoder再对嵌套的数据做解析
        dataGroup = ProtoDecoder(value).decode()
        parsedValue = dataGroup.toString()
    }

    override fun toString(): String {
        return parsedValue
    }

}