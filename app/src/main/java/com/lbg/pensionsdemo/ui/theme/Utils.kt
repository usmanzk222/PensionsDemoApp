package com.lbg.pensionsdemo.ui.theme

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

fun calculateAge(birthDate: Date): String{
    val birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val currentDate = LocalDate.now()

    val period = Period.between(birthLocalDate, currentDate)
    return "${period.years}${getAgeSuffix(period.years)}"
}

fun getAgeSuffix(number: Int): String {
    val lastDigit = number % 10
    val lastTwoDigits = number % 100

    return when {
        lastTwoDigits in 11..13 -> "th"
        lastDigit == 1 -> "st"
        lastDigit == 2 -> "nd"
        lastDigit == 3 -> "rd"
        else -> "th"
    }
}