package com.lbg.pensionsdemo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFF000000),
        fontWeight = FontWeight(510),
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFF000000),
        fontWeight = FontWeight(400),
        textAlign = TextAlign.Center,
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFF000000),
        fontWeight = FontWeight(400),
        textAlign = TextAlign.Center,
    ),
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFF000000),
        fontWeight = FontWeight(400),
        textAlign = TextAlign.Center,
    )
)

/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/