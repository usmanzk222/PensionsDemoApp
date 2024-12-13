package com.lbg.pensionsdemo.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import java.text.SimpleDateFormat
import java.util.Locale

data class UserResponse(
    val success: Boolean,
    val data: User?,
    val message: String
)
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val dob: String?,
    val age: Int,
    @SerializedName("ordinal_age")
    val ordinalAge: String,
    @SerializedName("pension_qty")
    val pensions: Int
)


fun User.toUserEntity(): UserEntity {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val formattedDOB = if (!dob.isNullOrEmpty()) {
        dateFormat.parse(dob)?.time
    } else {
        null
    }

    return UserEntity(
        id = id,
        name = name,
        email = email,
        dob = formattedDOB,
        age =  age,
        ordinalAge =  ordinalAge,
        pensions = pensions
    )
}