package com.lbg.pensionsdemo.ui.model

import com.lbg.pensionsdemo.domain.model.UserDomain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun UserDomain.mapToUIModel(): UserUiModel {

    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val formattedDateOfBirth = dateOfBirth?.let {
        dateFormat.format(Date(it))
    }
    return UserUiModel(
        id = id,
        name = name.split( " ").first(),
        email = email.ifEmpty { "Unknown" },
        dateOfBirth = formattedDateOfBirth,
        age = age,
        ordinalAge = ordinalAge,
        pensions = pensions
    )
}