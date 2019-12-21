package com.suifeng.protobufdemo.data

import java.lang.StringBuilder

/**
 *数据组，把byte数据流转成UnitData后保存在这里
 */
class DataGroup {
    private var datas: MutableList<UnitData> = mutableListOf()

    fun addData(unitData: UnitData) {
        unitData.parseValue()
        datas.add(unitData)
    }

    fun getDatas(): MutableList<UnitData> {
        return datas
    }

    override fun toString(): String {
        var sb = StringBuilder()
        for (data in datas) {
            sb.append(data.toString()).append("\n")
        }
        return sb.toString()
    }


}