package com.usman.pensionsdemo.ui.theme

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

fun calculateAge(birthDate: Date): String{
    val birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val currentDate = LocalDate.now()

    val period = Period.between(birthLocalDate, currentDate)
    return "${period.years}th"
}