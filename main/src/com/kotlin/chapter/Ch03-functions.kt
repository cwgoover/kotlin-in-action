package com.kotlin.chapter

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

// Basic Defining and Calling Functions
// If you want to expose a constant to Java code as a <code>public static final field</code>,
// to make its usage more natural, you can mark it with the <code>const</code> modifier
const val UNIX_LINE_SEPARATOR = "\n"

fun main(args: Array<String>) {
    createCollections()

    println("# Named arguments:")
    val s = setOf(1, 3, 5)
    println(joinToString(s, separator = "; ", prefix = "(", postfix = ")"))

    println("\n# Default parameter values:")
    println(joinToString(s))
    // When using the regular call syntax, you can omit only trailing arguments.
    println(joinToString(s, "; "))
    // If you use named arguments, you can omit some arguments from the middle
    // of the list and specify only the ones you need
    println(joinToString(s, prefix = "# "))

    println("\n# Extension functions:")
    // you can invoke joinToStrFinalVersion like a member of a class
    val list = arrayListOf(1, 2, 3)
    println(list.joinToStrFinalVersion(" "))

    println(listOf("one", "two", "three").join(" "))
    // Error: Type mismatch: inferred type is List<Int> but Collection<String> was expected.
//    println(listOf(1, 2, 8).join())

    println("Kotlin".lastChar())

    println("\n# No overriding for extension functions:")
    noOverrideExten()

    println("\n# Extension properties: ")
    extendProp()

    // Spread operator unpacks the array contents
    val li = listOf("args: ", *args)
    println(li)

    // The word to in this line of code isn’t a built-in construct, but rather
    // a method invocation of a special kind, called an infix call.
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    // destructuring declaration:
    // you can assign a pair of elements to two variables directly
    val (number, name) = 1 to "one"
}

// To allow a function to be called using the infix notation,
// you need to mark it with the infix modifier
infix fun Any.to(other: Any) = Pair(this, other)

fun createCollections() {
    // Kotlin doesn’t have its own set of collection classes
    val set = setOf(1, 7, 53)
    val list = listOf(1, 7, 53)
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    // javaClass is Kotlin’s equivalent of Java’s getClass()
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    println(list.last())    // get the last element in a list
    println(set.max())      // find a maximum in a collection of numbers
}

// # Default parameter values
fun <T> joinToString(collection: Collection<T>,
                     separator: String = " ",
                     prefix: String = "",
                     postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)     // Don’t append a separator before the first element.
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToStrFinalVersion(separator: String = ", ",
                                prefix: String = "",
                                postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {  // "this" refers to the receiver object: a collection of T
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// you can use a more specific type as a receiver type, not just a class
fun Collection<String>.join(separator: String = ", ",
                            prefix: String = "",
                            postfix: String = ""
) = joinToStrFinalVersion(separator, prefix, postfix)

// Adding methods to other people’s classes: extension functions and properties
fun String.lastChar(): Char = this.get(this.length - 1)     //"this" references can omit.

// Extension properties
val String.lastChar: Char
    get() = get(length - 1)

// If you define the same property on a StringBuilder, you can make it a var,
// because the contents of a StringBuilder can be modified
var StringBuilder.lastChar: Char
    get() = get(length - 1)                 // Property getter
    set(value: Char) {                      // Property setter
        this.setCharAt(length - 1, value)
    }
fun extendProp() {
    println("Kotlin".lastChar)

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}

open class View {
    open fun click() = println("View clicked")
}

class Button: View() {  // note "()", Button extends View
    override fun click() = println("Button clicked")
}

// The extension function is resolved statically
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun noOverrideExten() {
    val view: View = Button()
    view.click()    // overridden
    view.showOff()  // it doesn’t work that way for extensions
}

// # Making your code tidy: local functions and extensions
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Cannot save user ${user.id}: Name is empty")
    }
    if (user.address.isEmpty()) {   // Field validation is duplicated.
        throw IllegalArgumentException("Cannot save user ${user.id}: Address is empty")
    }
    // Save user to the database
}

// local functions have access to all parameters and variables of the enclosing function.
fun saveUserLocalFun(user: User) {
    fun validate(value: String, fieldName: String) {    // Declares a local function to validate any field
        if (value.isEmpty()) {
            throw IllegalArgumentException("Cannot save user ${user.id}: $fieldName is empty")
        }
    }
    // Calls the local function to validate the specific fields
    validate(user.name, "Name")
    validate(user.address, "Address")
    // Save user to the database
}

// To improve this example even further, you can move the validation logic into an extension function of the User class:
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            // have access to all variables of the class
            throw IllegalArgumentException("Cannot save user $id: $fieldName is empty")
        }
    }
    validate(name, "Name")   // have access to all variables of the class
    validate(address, "Address")
}
fun saveUserLocalFunImprove(user: User) {
    user.validateBeforeSave()
    // Save user to the database
}


/**
 * 1. The vararg keyword, which allows you to declare a function taking an arbitrary number of arguments
 * 2. An infix notation that lets you call some one-argument functions without ceremony
 * 3. Destructuring declarations that allow you to unpack a single composite value into multiple variables
 */
