package com.usman.pensionsdemo.ui.model

import com.usman.pensionsdemo.domain.model.CharacterDomain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun CharacterDomain.mapToUIModel(): CharacterUiModel {

    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    val formattedDateOfBirth = dateOfBirth?.let {
        dateFormat.format(Date(it))
    }
    return CharacterUiModel(
        id = id,
        name = name,
        actor = actor.ifEmpty { "Unknown" },
        species = species,
        house = house,
        alive = if (alive) "Yes" else "No",
        dateOfBirth = formattedDateOfBirth,
        image = image
    )
}