package com.suifeng.protobufdemo

import com.suifeng.protobufdemo.AddressBookProtos.Person

class AddPerson {
    companion object {
        fun createPerson(personName: String): Person {
            var personBuilder = Person.newBuilder()
            personBuilder.id = 12345
            personBuilder.name = personName
            personBuilder.email = "abc@abc.com"

            var phoneNumberBuilder = Person.PhoneNumber.newBuilder()
            phoneNumberBuilder.number = "12345678911"
            phoneNumberBuilder.type = Person.PhoneType.HOME
            personBuilder.addPhone(phoneNumberBuilder.build())

            phoneNumberBuilder = Person.PhoneNumber.newBuilder()
            phoneNumberBuilder.number = "10987654321"
            phoneNumberBuilder.type = Person.PhoneType.MOBILE
            personBuilder.addPhone(phoneNumberBuilder.build())

            return personBuilder.build()
        }
    }
}
