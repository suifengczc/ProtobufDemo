package com.suifeng.protobufdemo.decoder

import com.suifeng.protobufdemo.data.*
import java.util.LinkedList

class ProtoDecoder {

    constructor(byteArray: ByteArray) {
        byteArray.map { BitData(it) }.forEach { originData.add(it) }
    }

    constructor(bitDataList: MutableList<BitData>) {
        originData.addAll(bitDataList)
    }


    var originData: LinkedList<BitData> = LinkedList()

    private var messages: DataGroup = DataGroup()

    private var curDataType = DATA_TYPE_WIRE
    private var curWireType: Int = WIRE_TYPE_0


    fun decode(): DataGroup {
        while (!originData.isEmpty()) {
            var unitData = createUnitData()
            var bitData: BitData
            do {
                bitData = originData.removeFirst()
                unitData.addValue(bitData)
            } while (bitData.hasNext())
            messages.addData(unitData)
            curDataType = if (unitData is TypeData) {
                val wireType = unitData.getWireType()
                curWireType = wireType.toInt(2)
                val id = unitData.getID()
                if (wireType.toInt(2) == 2) {
                    DATA_TYPE_LENGTH
                } else {
                    DATA_TYPE_VALUE
                }
            } else if (unitData is LengthData) {
                val length = unitData.parseValue().toRadixNum(2)
                unitData = createUnitData(DATA_TYPE_VALUE, curWireType)
                for (i in 1..length.toInt()) {
                    unitData.addValue(originData.removeFirst())
                }
                messages.addData(unitData)
                DATA_TYPE_WIRE
            } else {
                DATA_TYPE_WIRE
            }
        }
        return messages
    }

    private fun createUnitData(dataType: Int, wireType: Int): UnitData {
        return when (dataType) {
            DATA_TYPE_LENGTH -> LengthData()
            DATA_TYPE_WIRE -> TypeData()
            DATA_TYPE_VALUE -> when (wireType) {
                WIRE_TYPE_0, WIRE_TYPE_1, WIRE_TYPE_5 -> Value1SInt()
                WIRE_TYPE_2 -> Value2RepeatData()
                else -> ValueData()
            }
            else -> TypeData()
        }
    }

    private fun createUnitData(): UnitData {
        return createUnitData(curDataType, curWireType)
    }

}

fun String.toRadixNum(radix: Int): String {
    return if (this.length > 32) {
        this.toLong(radix).toString()
    } else {
        this.toInt(radix).toString()
    }
}