package com.lbg.pensionsdemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lbg.pensionsdemo.domain.model.UserDomain

@Entity
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val dob: Long?,
    val age: Int,
    val ordinalAge: String,
    val pensions: Int
)

fun UserEntity.toUserDomain(): UserDomain{
    return UserDomain(
        id = id,
        name = name,
        email = email,
        dateOfBirth = dob,
        age = age,
        ordinalAge = ordinalAge,
        pensions = pensions
    )
}