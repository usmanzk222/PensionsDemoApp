package com.lbg.pensionsdemo.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUiModel(
    val id: Int,
    val name: String,
    val email: String,
    val dateOfBirth: String?,
    val age: Int,
    val ordinalAge: String,
    val pensions: Int
): Parcelable