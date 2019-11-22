package com.suifeng.protobufdemo

import com.suifeng.protobufdemo.AddressBookProtos.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class AddressBookProtobuf {
    companion object {
        fun encodeTest(names: Array<String>): File {
            var bookBuilder = AddressBook.newBuilder()

            for (name in names) {
                bookBuilder.addPerson(AddPerson.createPerson(name))
            }
            var book = bookBuilder.build()
            var outFile = File("E:\\out")
            var fileOutputStream = FileOutputStream(outFile)
            book.writeTo(fileOutputStream)
            return outFile
        }
    }

}