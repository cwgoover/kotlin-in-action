package com.kotlin.main

import com.kotlin.main.test.MiscUtils

fun main() {
    val oldDate = "Jul 1, 2019"
    val date = MiscUtils.convertDateFormatToNewString(oldDate, MiscUtils.dateDisplayFormat, MiscUtils.dateValueFormat)
    println("oldDate=$oldDate, newDate=$date")
}