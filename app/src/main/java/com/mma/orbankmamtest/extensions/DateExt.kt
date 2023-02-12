package com.mma.orbankmamtest.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

const val YearMonthDayServerFormat = "yyyy-MM-dd'T'HH:mm:ssXXX"

fun String.toYearMonthDayServerFormat(): LocalDate {
    val df = ofPattern(YearMonthDayServerFormat)
    return LocalDate.parse(this, df)
}