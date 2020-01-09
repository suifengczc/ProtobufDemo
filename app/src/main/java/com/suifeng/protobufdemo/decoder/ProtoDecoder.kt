package com.suifeng.protobufdemo.decoder

import com.suifeng.protobufdemo.data.*
import com.suifeng.protobufdemo.data.value.ValueData
import com.suifeng.protobufdemo.data.value.ValueFixedData
import com.suifeng.protobufdemo.data.value0.Value0SInt
import com.suifeng.protobufdemo.data.value1.Value1Double
import com.suifeng.protobufdemo.data.value2.Value2EmbeddedData
import com.suifeng.protobufdemo.data.value2.Value2RepeatData
import com.suifeng.protobufdemo.data.value2.Value2StringData
import com.suifeng.protobufdemo.data.value5.Value5Float
import java.util.*

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


    /**
     * 对originData按照T-L-V结构解析
     */
    fun decode(): DataGroup {
        while (!originData.isEmpty()) {
            var unitData = createUnitData()
            var bitData: BitData
            //先判断当前要处理的数据类型
            when (curDataType) {
                DATA_TYPE_VALUE -> {
                    //VALUE数据块类型根据同组的wiretype类型不同有不同处理
                    when (curWireType) {
                        //wiretype == 1时，数据块是8字节定长
                        WIRE_TYPE_1 -> {
                            for (i in 1..8) {
                                bitData = originData.removeFirst()
                                unitData.addValue(bitData)
                            }
                        }
                        //wiretype == 5时，数据块是8字节定长
                        WIRE_TYPE_5 -> {
                            for (i in 1..4) {
                                bitData = originData.removeFirst()
                                unitData.addValue(bitData)
                            }
                        }
                        //默认按照可变长处理
                        else -> {
                            do {
                                bitData = originData.removeFirst()
                                unitData.addValue(bitData)
                            } while (bitData.hasNext())
                        }
                    }
                }
                //其他类型默认按照可变长处理
                else -> {
                    do {
                        bitData = originData.removeFirst()
                        unitData.addValue(bitData)
                    } while (bitData.hasNext())
                }
            }

            //添加数据块
            messages.addData(unitData)

            //判断后一个数据块的类型
            curDataType = if (unitData is TypeData) {
                //TypeData后边可能是Length数据块或Value数据块，当wiretype == 2时才有Length数据块
                val wireType = unitData.getWireType()
                curWireType = wireType.toInt(2)
                if (curWireType == 2) {
                    DATA_TYPE_LENGTH
                } else {
                    DATA_TYPE_VALUE
                }
            } else if (unitData is LengthData) {
                //LengthData数据块中顺便把Value数据块处理掉
                val length = unitData.getParsedValue()
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
                WIRE_TYPE_0 -> ValueData()
                WIRE_TYPE_1 -> ValueFixedData()
                WIRE_TYPE_2 -> Value2RepeatData()
                WIRE_TYPE_5 -> ValueFixedData()
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