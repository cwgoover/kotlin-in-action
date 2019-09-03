package com.kotlin.main.test

import java.text.SimpleDateFormat

object MiscUtils {
    const val dateValueFormat: String = "yyyy-MM-dd"
    const val dateDisplayFormat: String = "MMM d, yyyy"

    fun convertDateFormatToNewString(oldString: String?, oldFormat: String, newFormat: String): String {
        val sdf = SimpleDateFormat(oldFormat)
        val d = sdf.parse(oldString)
        sdf.applyPattern(newFormat)
        return sdf.format(d)
    }
}