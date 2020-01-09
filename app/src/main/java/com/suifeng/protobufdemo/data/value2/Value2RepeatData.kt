package com.suifeng.protobufdemo.data.value2

import com.suifeng.protobufdemo.data.DataGroup
import com.suifeng.protobufdemo.data.UnitData
import com.suifeng.protobufdemo.data.value.ValueData

/**
 * value数据中wireType == 2 时的packed repeated fields和bytes
 */
class Value2RepeatData : UnitData() {
    /**
     * 传入的value只有一串byte流，不知道具体是什么类型
     */
    override fun parseValue() {
        val dataGroup = DataGroup()
        var valueData = ValueData()
        value.forEach {
            valueData.addValue(it)
            if (!it.hasNext()) {
                dataGroup.addData(valueData)
                valueData = ValueData()
            }
        }
        parsedValue = dataGroup.toString()
    }

    override fun toString(): String {
        return parsedValue
    }

}