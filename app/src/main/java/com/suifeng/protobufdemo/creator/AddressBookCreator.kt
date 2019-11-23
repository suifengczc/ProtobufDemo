package com.suifeng.protobufdemo.creator

import com.suifeng.protobufdemo.AddressBookProtos.*

class AddressBookCreator {
    companion object {
        fun createAddressBook(names: Array<String>): AddressBook {
            val bookBuilder = AddressBook.newBuilder()

            for (name in names) {
                bookBuilder.addPerson(
                    PersonCreator.createPerson(
                        name
                    )
                )
            }
            return bookBuilder.build()
        }
    }

}