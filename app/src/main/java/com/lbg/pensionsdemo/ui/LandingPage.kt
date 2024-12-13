package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbg.pensionsdemo.R

@Composable
fun LandingPage(paddingValues: PaddingValues) {
    Image(
        painter = painterResource(R.drawable.home_screen),
        contentDescription = "Home background",
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(paddingValues),
        contentScale = ContentScale.Crop
    )
}


@Preview
@Composable
fun LandingPreview() {
    LandingPage(PaddingValues(16.dp))
}