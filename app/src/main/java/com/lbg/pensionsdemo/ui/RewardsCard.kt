package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbg.pensionsdemo.R
import com.lbg.pensionsdemo.ui.theme.AppBackground
import com.lbg.pensionsdemo.ui.theme.LoadLottieAnimation
import com.lbg.pensionsdemo.ui.theme.SWButton

@Composable
fun RewardsCard(modifier: Modifier,
                onGotItClick: () -> Unit) {
    Card(
        modifier = modifier
            .shadow(8.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp)),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppBackground
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.reward_screen_heading),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.reward_screen_description),
                style = MaterialTheme.typography.bodyMedium,
            )

            LoadLottieAnimation(350, 24, R.raw.piggy_bank, Modifier)

            Box(modifier = Modifier.size(width = 250.dp, height = 120.dp)) {
                RewardCoinCardAnimation()
            }

            SWButton(onClick = onGotItClick, buttonText = stringResource(R.string.got_it_button))
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun PreviewLargeToastScreen() {
    RewardsCard(modifier = Modifier) {}
}
