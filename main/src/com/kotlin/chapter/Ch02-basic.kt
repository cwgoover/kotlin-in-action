package com.kotlin.chapter

import com.kotlin.chapter.Color.*
import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException
import java.util.*

// Basic
fun main() {
    println("${mix(BLUE, YELLOW)}\n")
    // (1+2)+4
//    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))

    // for the numbers from 1 to 100
    println("FizzBuzz game starts:")
    for (i in 1..100) {
        println(fizzBuzz(i))
    }
    // Let’s start counting backward from 100 and include only even numbers:
    println("Another FizzBuzz game starts:")
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }

    iterMap()

    println(recognize('8'))

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    val reader2 = BufferedReader(StringReader("not a number"))
    readNumberExpr(reader2)
}

// 'try', 'catch', and 'finally'
fun readNumber(reader: BufferedReader): Int? {
    return try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        reader.close()
    }
}

// 'try' as an expression
fun readNumberExpr(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
//        null
        return
    }
    println(number)
}


// iterate over a map
fun iterMap() {
    // create a map
    val binaryReps = TreeMap<Char, String>()

    // fill it with binary representations of some letters
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())  //Converts ASCII code to binary
        binaryReps[c] = binary
    }

    // prints binary representations for characters.
    for ((letter, binary) in binaryReps) {      //iterate over a map
        println("$letter = $binary")
    }
}

// use the in operator to check whether a value is in a range
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

// use the in operator to check if a value isn’t in a range
fun isNotDigit(c: Char) = c !in '0'..'9'

// The in and !in operators also work in when expressions:
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun mix(c1: Color, c2: Color) =
        when (setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO
            else ->
                throw Exception("Dirty color")
        }

fun mixOptimized(c1: Color, c2: Color) =
        when {
            (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) ->
                ORANGE
            (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) ->
                GREEN
            (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) ->
                INDIGO
            else ->
                throw Exception("Dirty color")
        }

fun eval(e: Expr): Int = when (e) {
        is Num ->
            e.value
        is Sum ->
            eval(e.left) + eval(e.right)
        else ->
            throw IllegalArgumentException("Unknown expression")
    }


interface Expr

class Num(val value: Int) : Expr

class Sum(val left: Expr, val right: Expr) : Expr


enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0),
    BLUE(0, 0, 255), INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);   // The semicolon here is required.

    fun rgb() = (r * 256 + g) * 256 + b
}