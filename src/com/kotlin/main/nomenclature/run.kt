package com.kotlin.main.nomenclature

/**
 * Kotlin run expression can change the outer property. Hence in the above code, we’ve redefined it for the local scope.
 * * Similar to the let function, the run function also returns the last statement.
 * * Unlike let, the run function doesn’t support the it keyword.
 */
fun main() {
    var tutorial = "This is Kotlin Tutorial"
    println(tutorial) //This is Kotlin Tutorial
    tutorial = run {
        val tutorial = "This is run function"
        tutorial
    }
    println(tutorial) //This is run function

    combineRunLet()
}

fun combineRunLet() {
    var p : String? = null
    p?.let { println("p is $p") } ?: run { println("p was null. Setting default value to: ")
        p = "Kotlin"}

    println(p)
    //Prints
    //p was null. Setting default value to:
    //Kotlin
}