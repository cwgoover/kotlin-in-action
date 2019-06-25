package com.kotlin.main

// Multiline triple-quoted strings
// One of the areas where multiline strings can be useful in your programs (besides games that use ASCII art) is tests.
val kotlinLogo = """| //
     .|//
     .|/ \"""


fun main() {
    // KNOWLEDGE:
    // split that have different arguments. The one that takes a regular expression requires a value of Regex type, not String.
    // Creates a regular expression explicitly
    println("12.345-6.A".split("\\.|-".toRegex())) // in Kotlin you use an extension function toRegex to convert a string into a regular expression.

    // Specifies several delimiters, takes an arbitrary number of delimiters as plain-text strings
    println("12.345-6.A".split(".", "-"))

    // splitting a path into a directory, a filename, and a file extension
    println("\n/Users/yole/kotlin-book/chapter.adoc")
    println("parsePath:")
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    println("parsePathRegexp:")
    parsePathRegexp("/Users/yole/kotlin-book/chapter.adoc")

    // If you want a better representation of such a string, you can trim the indentation (in other words, the left margin)
    println(kotlinLogo.trimMargin("."))

    // Because multiline strings don’t support escape sequences, you have to use an embedded expression
    // if you need to use a literal dollar sign in the contents of your string.
    val price = """${'$'}99.9"""
    println(price)
    println("""$99.9""")
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val file = path.substringAfterLast("/")

    val fileName = file.substringBeforeLast(".")
    val extension = file.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathRegexp(path: String) {
    // KNOWLEDGE: triple-quoted string
    // 1. In such a string, you don’t need to escape any characters, including the backslash,
    //    so you can encode the dot symbol with \. rather than \\. as you’d write in an ordinary string literal
    // 2. (Multiline triple-quoted strings) Such a string literal can contain any characters, including line breaks.
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}