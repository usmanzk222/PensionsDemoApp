package com.lbg.pensionsdemo.domain.model

import androidx.compose.ui.graphics.Color

enum class House(val color: Color) {
    GRYFFINDOR(Color(0xFF740001)),
    SLYTHERIN(Color(0xFF1A472A)),
    RAVENCLAW(Color(0xFF0E1A40)),
    HUFFLEPUFF(Color(0xFFEEB939)),
    UNKNOWN(Color.LightGray)
}