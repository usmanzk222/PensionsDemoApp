package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbg.pensionsdemo.R

@Composable
fun BingoScreen() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScreenTitle(Modifier)
    }
}

@Composable
fun ScreenTitle(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.personalised_next_steps_title),
            style = headlineLarge
        )
    }
}

@Composable
fun UserRewardsCard() {

}

@Composable
fun BingoGrid() {

}

@Composable
fun BingoCell() {

}

@Composable
fun OneAwaySpeechBubble() {

}

@Preview(showBackground = true)
@Composable
fun BingoScreenPreview() {
    BingoScreen()
}