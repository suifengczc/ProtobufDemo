package com.suifeng.protobufdemo

import com.google.protobuf.ByteString
import com.suifeng.protobufdemo.decoder.ProtoDecoder

fun main() {
    val test1Builder = Test.Test1.newBuilder()
    //wiretype == 0
    test1Builder.a = 10
    test1Builder.b = 1000000000000000000
    test1Builder.c = 20
    test1Builder.d = 2000000000000000000
    test1Builder.e = 30
    test1Builder.f = 3000000000000000000
    test1Builder.g = true
    test1Builder.h = Test.Test1.PhoneType.WORK

    //wiretype == 1
    test1Builder.i = 4000000000000000000
    test1Builder.j = 5000000000000000000
    test1Builder.k = 50.0023

    //wiretype == 2
    test1Builder.l = "hello, proto"
    test1Builder.m = ByteString.copyFrom(byteArrayOf(1, 2, 3))
    test1Builder.n = Test.Test1.Test2.newBuilder().setA(10).build()
    test1Builder.addO(1)
    test1Builder.addO(2)
    test1Builder.addO(3)

    //wiretype == 5
//    test1Builder.p = 40
//    test1Builder.q = 50
//    test1Builder.r = 11.01F

    val build = test1Builder.build()
    val byteArray = build.toByteArray()

    byteArray.let {
        for (byte in it) {
            println(String.format("%x", byte))
        }
    }
    println("---------------------------------------")
    val decode = ProtoDecoder(byteArray).decode()
    println(decode.toString())


}