package com.lbg.pensionsdemo.domain.model

data class CharacterDomain(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: House,
    val alive: Boolean,
    val dateOfBirth: Long?,
    val image: String?
)