package com.lbg.pensionsdemo.data.remote.model

import com.lbg.pensionsdemo.data.local.entity.CharacterEntity
import com.lbg.pensionsdemo.domain.model.House
import java.text.SimpleDateFormat
import java.util.Locale

data class Character(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: House,
    val alive: Boolean,
    val dateOfBirth: String?,
    val image: String?
)


fun Character.toCharacterEntity(): CharacterEntity {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val formattedDOB = if (!dateOfBirth.isNullOrEmpty()) {
        dateFormat.parse(dateOfBirth)?.time
    } else {
        null
    }

    return CharacterEntity(
        id = id,
        name = name,
        actor = actor,
        species = species,
        house = house,
        alive = alive,
        dateOfBirth = formattedDOB,
        image = image
    )
}