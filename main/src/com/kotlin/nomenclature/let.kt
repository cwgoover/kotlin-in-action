package com.kotlin.nomenclature

//let takes the object it is invoked upon as the parameter and returns the result of the lambda expression.
//Kotlin let is a scoping function wherein the variables declared inside the expression cannot be used outside.
fun main(args: Array<String>) {
    var str = "Hello Kotlin let"
    str.let { println("$it!!") }
    println(str)
    //Prints
    //Hello World!!
    //Hello World

    //it keyword contains the copy of the property inside let.
    //The last value from the let is returned as an argument as shown below.
    var strLength = str.let { "$it function".length }
    println("strLength is $strLength") //prints strLength is 25

    println("Chaining let functions:")
    ChainingLetFunctions()

    println("Nesting let:")
    nestingLet()

//    println("let for null checks:")
    checkNull()
}

// As you can see we’ve declared a local variable “i” inside the second let function.
// Setting the last statement of the let function to i returns the property to the
// outer property a.
fun ChainingLetFunctions() {
    var a = 1
    var b= 2

    a = a.let { it + 2 }.let { val i = it + b
        i}
    println(a) //5
}

//We can set a let expression inside another let expression as shown below.
fun nestingLet() {
    var x = "Anupam"
    x.let { outer -> outer.let { inner -> println("Inner is: ($inner) and outer is: ($outer)") } }
    //Prints
    //Inner is Anupam and outer is Anupam

    //For nested let, we can’t use it keyword. We need to assign explicit names to it in both the let functions.
    //Only the outermost let returns the value as shown below.
    x = x.let { outer ->
        outer.let { inner ->
            println("Inner is ($inner) and outer is ($outer)")
            "Kotlin Tutorials Inner let"
        }
        "Kotlin Tutorials Outer let"
    }
    println(x) //prints Kotlin Tutorials Outer let
}

//let is useful for checking Nullable properties as shown below.
fun checkNull() {
    var name : String? = "Kotlin let null check"
    name?.let { println(it) } //prints Kotlin let null check
    name = null
    name?.let { println(it as String?) } //nothing happens
}

