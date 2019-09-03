package com.kotlin.main.nomenclature

//As the name says, also expressions does some additional processing on the object it was invoked.
//Unlike let, it returns the original object instead of any new return data. Hence the return data has always the same type.
//Like let, also uses it too.
fun main() {
    var m = 1
    m = m.also { it + 1 }.also { it + 1 }
    println(m) //prints 1

    letVsAlso()
}

fun letVsAlso() {
    data class Person(var name: String, var tutorial : String)

    val person = Person("Anupam", "Kotlin")

    val l = person.let { it.tutorial = "Android" }
    val al = person.also { it.tutorial = "Android" }

    //The also expression returns the data class object whereas the let expression
    // returns nothing (Unit) as we didn’t specify anything explicitly.
    println(l)
    println(al)
    println(person)
}