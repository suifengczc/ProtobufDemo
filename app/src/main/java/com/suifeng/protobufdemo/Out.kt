package com.suifeng.protobufdemo

import com.suifeng.protobufdemo.decoder.ProtoDecoder

fun main() {
    val test1Builder = Test.Test1.newBuilder()
//    test1Builder.a = 10
//    test1Builder.b = 1000000000000000000
//    test1Builder.c = 20
//    test1Builder.d = 2000000000000000000
//    test1Builder.e = 30
//    test1Builder.f = 3000000000000000000
//    test1Builder.g = true
//    test1Builder.h = Test.Test1.PhoneType.WORK
//    test1Builder.i = "hello,proto buf"
//    test1Builder.i = "a"
    test1Builder.addK(1)
    test1Builder.addK(2)
//    test1Builder.addK(3)


//    var teset2Builder = Test.Test1.Test2.newBuilder()
//    teset2Builder.a = 5
//    var test2 = teset2Builder.build()
//    test1Builder.j = test2
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