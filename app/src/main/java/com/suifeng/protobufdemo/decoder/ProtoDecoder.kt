package com.suifeng.protobufdemo.decoder

import com.suifeng.protobufdemo.data.BitData
import com.suifeng.protobufdemo.data.ProtoMessageData

class ProtoDecoder(byteArray: ByteArray) {
    var originData: Array<BitData>
    var index: Int

    var messages: MutableList<ProtoMessageData> = mutableListOf<ProtoMessageData>()


    init {
        originData = byteArray.map { it -> BitData(it) }.toTypedArray()
        index = 0
    }

    fun decode(): MutableList<ProtoMessageData> {
        while (index < originData.size) {

            val value: MutableList<String> = mutableListOf()
            val bitData = originData[index]
            val wireType = bitData.binaryString.substring(5..7).toRadixInt(2)
            val fieldNum = bitData.binaryString.substring(2..4).toRadixInt(2)
            when (wireType) {
                0 -> {
                    do {
                        value.add(originData[++index].binaryString)
                    } while (originData[index].hasNext())
                }
                1 -> {
                    for (i in 0..7) {
                        value.add(originData[index++].binaryString)
                    }
                }
                2 -> {
                    val length = originData[++index].binaryString.toRadixInt(2)
                    for (i in 0 until length) {
                        value.add(originData[++index].binaryString)
                    }
                }
                5 -> {
                    for (i in 0..3) {
                        value.add(originData[i].binaryString)
                    }
                }
                else -> {
                }
            }
            messages.add(
                ProtoMessageData(
                    wireType,
                    fieldNum,
                    fieldNum,
                    value
                )
            )
            index++
        }
        return messages
    }

}

fun String.toRadixInt(radix: Int): Int {
    return Integer.parseInt(this, radix)
}