package com.suifeng.protobufdemo

import com.suifeng.protobufdemo.decoder.ProtoDecoder

fun main() {
    val newBuilder = Test.Test1.newBuilder()
    newBuilder.a = 150
    newBuilder.b = "testing"
    val build = newBuilder.build()
    val byteArray = build.toByteArray()
    byteArray.let {
        for (byte in it) {
            println(String.format("%x", byte))
        }
    }
    println("---------------------------------------")
    val decode = ProtoDecoder(byteArray).decode()
    for (protoMessage in decode) {
        println(protoMessage)
    }


}