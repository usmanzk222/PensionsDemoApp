package com.usman.pensionsdemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.usman.pensionsdemo.domain.model.CharacterDomain
import com.usman.pensionsdemo.domain.model.House

@Entity
data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: House,
    val alive: Boolean,
    val dateOfBirth: Long?,
    val image: String?
)

fun CharacterEntity.toCharacterDomain(): CharacterDomain{
    return CharacterDomain(
        id = id,
        name = name,
        actor = actor,
        species = species,
        house = house,
        alive = alive,
        dateOfBirth = dateOfBirth,
        image = image
    )
}