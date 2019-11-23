package com.suifeng.protobufdemo.creator

import com.suifeng.protobufdemo.AddressBookProtos.Person

class PersonCreator {
    companion object {
        fun createPerson(personName: String): Person {
            val personBuilder = Person.newBuilder()
            personBuilder.id = 12345
            personBuilder.name = personName
            personBuilder.email = "abc@abc.com"

            var phoneNumberBuilder = Person.PhoneNumber.newBuilder()
            phoneNumberBuilder.number = "123"
            phoneNumberBuilder.type = Person.PhoneType.HOME
            personBuilder.addPhone(phoneNumberBuilder.build())

            phoneNumberBuilder = Person.PhoneNumber.newBuilder()
            phoneNumberBuilder.number = "456"
            phoneNumberBuilder.type = Person.PhoneType.MOBILE
            personBuilder.addPhone(phoneNumberBuilder.build())

            return personBuilder.build()
        }
    }
}
