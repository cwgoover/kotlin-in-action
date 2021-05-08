package com.kotlin.nomenclature

//Like apply, with is used to change instance properties without the need to call dot
// operator over the reference every time.
fun main() {
    data class Person(var name: String, var tutorial : String)
    var person = Person("Anupam", "Kotlin")

    with(person)
    {
        name = "No Name"
        tutorial = "Kotlin tutorials"
    }
    println(person)

    applyVsWith()
}

/**
 * Kotlin apply vs with
 * * with runs without an object(receiver) whereas apply needs one.
 * * apply runs on the object reference, whereas with just passes it as an argument.
 * * The last expression of with function returns a result.
 */
fun applyVsWith() {
    data class Person(var name: String, var tutorial : String)
    var person = Person("Anupam", "Kotlin")

    var xyz = with(person)
    {
        name = "No Name"
        tutorial = "Kotlin tutorials"
        val xyz = "End of tutorial"
        xyz
    }
    println(xyz) //End of tutorial
}