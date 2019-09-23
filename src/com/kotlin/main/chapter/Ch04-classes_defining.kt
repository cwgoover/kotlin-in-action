package com.kotlin.main.chapter

import java.io.Serializable

// Classes, objects, and interface
fun main() {
    // using incorrect syntax. Variables in Kotlin are declared like:
    // [var/val] name: OptionalExplicitType = something
//    var Ch04.Button btn = Ch04.Button()
    val btn: Ch04.Button = Ch04.Button()
    val btn1 = Ch04.Button()

    btn.click()
    btn.showOff()
    btn1.setFous(true)
}

class Ch04 {

    /** 4.1.1 Interfaces in Kotlin: methods with default implementations **/
    interface Clickable {
        // abstract method
        fun click()

        fun showOff() = println("I'm clickable!")   // Method with a default implementation, you just provide a method body.
    }

    interface Focusable {
        fun setFous(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")

        fun showOff() = println("I'm focusable!")
    }

    // Kotlin uses the colon after the class name to replace both the extends and implements keywords used in Java.
    class Button : Clickable, Focusable {
        override fun click() = println("I was clicked")

        // You must provide an explicit implementation if more than one implementation for the same member is inherited.
        override fun showOff() {
            super<Clickable>.showOff()
            super<Focusable>.showOff()
        }
    }

    /** 4.1.2 Open, final, and abstract modifiers: final by default **/
    // Whereas Java’s classes and methods are open by default, Kotlin’s are final by default.
    // If you want to allow the creation of subclasses of a class, you need to mark the class with the open modifier.
    // In addition, you need to add the open modifier to every property or method that can be overridden

    // This class is open: others can inherit from it.
    open class RichButton : Clickable {

        // This function is "final": you can’t override it in a subclass.
        fun disable() {}

        // This function is open: you may override it in a subclass.
        open fun animate() {}

        // This function overrides an open function and is open as well.
        override fun click() {}

        // If you override a member of a base class or interface, the overriding member will also be open by default.
        // If you want to change this and forbid the subclasses of your class from overriding your implementation,
        // you can explicitly mark the overriding member as final
        final override fun showOff() {}
    }

    // This class is abstract: you can’t create an instance of it.
    abstract class Animated {

        // This function is abstract: it doesn’t have an implementation and must be overridden in subclasses.
        abstract fun animate()

        // Non-abstract functions in abstract classes aren’t open by default but can be marked as open.
        open fun stopAnimating() {}

        fun animateTwice() {}
    }

    /** 4.1.3 Visibility modifiers: public by default **/
    // Visibility modifiers in Kotlin are similar to those in Java. You have the same public, protected,
    // and private modifiers. But the default visibility is different: if you omit a modifier, the declaration becomes public.

    // The default visibility in Java, package-private, isn’t present in Kotlin.
    // Kotlin uses packages only as a way of organizing code in namespaces; it doesn’t use them for visibility control.

    // As an alternative, Kotlin offers a new visibility modifier, **internal**, which means "visible inside a module."

    // You may mark such declarations private, which means "visible inside the containing file."
    // You can also make a class private if it should be used only in a single file.

    internal open class TalkativeButton : Focusable {
        private fun yell() = println("Hey!")
        protected fun whisper() = println("Let's talk!")
    }

    // Kotlin forbids you to reference the less-visible type TalkativeButton (internal, in this case)
    // from the public function giveSpeech.
    /* internal */fun TalkativeButton.giveSpeech() {
        yell()
        // A protected member is only visible in the class and its subclasses.
        // Extension functions of a class don’t get access to its private or protected members.
        whisper()
    }

    /** 4.1.4 Inner And nested classes: nested by default **/
    //  The difference is that Kotlin nested classes don’t have access to the outer class instance,
    //  unless you specifically request that.

    // A nested class in Kotlin with no explicit modifiers is the same as a static nested class in Java.
    // To turn it into an inner class, so that it contains a reference to an outer class, you use the **inner** modifier.

    interface State: Serializable

    interface View {
        fun getCurrentState(): State
        fun restoreState(state: State) {}
    }

    class OuterButton : View {

        override fun getCurrentState(): State = InnerButtonState()

        override fun restoreState(state: State) { /*...*/ }

        // This class is an analogue of a static nested class in Java.
        class NestedButtonState : State { /*...*/ }

        inner class InnerButtonState : State {
            // The syntax to reference an instance of an outer class in Kotlin also differs from Java.
            // You write this@Outer to access the Outer class from the Inner class:
            fun getOuterReference(): OuterButton = this@OuterButton
        }
    }

    /** 4.1.5 Sealed classes: defining restricted class hierarchies **/
    // You mark a superclass with the **sealed** modifier, and that restricts the possibility of creating subclasses.
    // All the direct subclasses must be nested in the superclass, and a subclass can’t be made a data class

    // Mark a base class as sealed...
    sealed class Expr {
        // ...and list all the possible subclasses as nested classes.
        class Num(val value: Int) : Expr()
        class Sum(val left: Expr, val right: Expr): Expr()
    }

    fun eval(e: Expr): Int =
            // The "when" expression covers all possible cases, so no "else" branch is needed.
            when (e) {
                is Expr.Num -> e.value
                is Expr.Sum -> eval(e.left) + eval(e.right)
            }

}
