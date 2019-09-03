package com.kotlin.main

// Classes, objects, and interface
fun main() {
    // using incorrect syntax. Variables in Kotlin are declared like:
    // [var/val] name: OptionalExplicitType = something
//    var Ch04.Button btn = Ch04.Button()
    var btn: Ch04.Button = Ch04.Button()
    var btn1 = Ch04.Button()

    btn.click()
    btn1.click()
}

class Ch04 {

    interface Clickable {
        // abstract method
        fun click()

        fun showOff() = println("I'm clickable!")   // Method with a default implementation
    }

    class Button : Clickable {
        override fun click() = println("I was clicked")
    }

}
