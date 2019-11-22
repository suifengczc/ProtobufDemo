package com.suifeng.protobufdemo

import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    var newBuilder = Test.Test1.newBuilder()
    newBuilder.a = 150
    var build = newBuilder.build()
    build.toByteArray().let {
        for (byte in it) {
            println(String.format("%x", byte))
        }
    }

}