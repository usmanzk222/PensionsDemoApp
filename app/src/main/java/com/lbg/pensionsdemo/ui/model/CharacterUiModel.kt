package com.lbg.pensionsdemo.ui.model

import android.os.Parcelable
import com.lbg.pensionsdemo.domain.model.House
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUiModel(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: House,
    val alive: String,
    val dateOfBirth: String?,
    val image: String?
): Parcelable