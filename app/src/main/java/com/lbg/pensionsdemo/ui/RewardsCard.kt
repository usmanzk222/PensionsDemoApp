package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbg.pensionsdemo.R
import com.usman.pensionsdemo.ui.theme.LoadLottieAnimation
import com.usman.pensionsdemo.ui.theme.SWButton

@Composable
fun RewardsCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
    ) {
        Column( // Use Column for vertical arrangement
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between items
        ) {
            Text(
                text = stringResource(R.string.reward_screen_heading),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.reward_screen_description),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            LoadLottieAnimation(320, 24, R.raw.piggy_bank, Modifier)

            // Constrain animation size
            Box(modifier = Modifier.size(width = 200.dp, height = 100.dp)) {
                RewardCoinCardAnimation()
            }

            SWButton(onClick = {}, buttonText = stringResource(R.string.got_it_button))
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun PreviewLargeToastScreen() {
    RewardsCard(modifier = Modifier)
}
