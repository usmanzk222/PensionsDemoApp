package com.lbg.pensionsdemo.domain.model

data class UserDomain(
    val id: Int,
    val name: String,
    val email: String,
    val dateOfBirth: Long?,
    val age: Int,
    val ordinalAge: String,
    val pensions: Int
)
