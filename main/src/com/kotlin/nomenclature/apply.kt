package com.kotlin.nomenclature

//Kotlin apply is an extension function on a type. It runs on the object reference
// (also known as receiver) into the expression and returns the object reference on
// completion.
fun main() {
    data class Person(var name: String, var tutorial : String)

    var person = Person("Anupam", "Kotlin")

    person.apply { this.tutorial = "Swift" }
    println(person)

    applyVsAlso()
}

// Note: In apply it isn’t allowed. If the property name of the data class is unique in the function, you can omit this.
//We should use also only when we don’t want to shadow this.
fun applyVsAlso() {
    data class Person(var n: String, var t : String)

    val person = Person("Anupam", "Kotlin")

    person.apply { t = "Swift" }
    println(person)

    person.also { it.t = "Kotlin" }
    println(person)
}