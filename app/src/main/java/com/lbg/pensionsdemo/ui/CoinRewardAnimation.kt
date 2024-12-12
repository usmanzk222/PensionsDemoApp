package com.lbg.pensionsdemo.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbg.pensionsdemo.R
import com.lbg.pensionsdemo.ui.theme.RewardCoinCard
import com.lbg.pensionsdemo.ui.theme.RewardCoinCardLighter
import kotlinx.coroutines.delay

@Composable
fun RewardCoinCardAnimation() {
    var animationStarted by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animationStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 500)
    )
    val offsetYInitial = -200.dp
    var offsetY by remember { mutableStateOf(offsetYInitial) }
    val trailCards = remember { mutableStateListOf<Float>() }

    LaunchedEffect(key1 = true) {
        delay(500)
        animationStarted = true

        while (offsetY.value < 0) {
            offsetY += 2.dp
            trailCards.add(offsetY.value) // Add current offset to trailCards
            delay(80)

            // Remove trail cards that are off-screen
            trailCards.removeAll { it < offsetYInitial.value }
        }

        // Stop creating trail cards and clear existing ones
        trailCards.clear()
        offsetY = 0.dp // Set final position
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CoinRewardCard( // Main card
            modifier = Modifier
                .offset(y = offsetY)
                .scale(scale)
        )

        trailCards.forEach { trailOffsetY -> // Trail cards
            CoinRewardCard(
                modifier = Modifier
                    .offset(y = trailOffsetY.dp)
                    .alpha(1 - (trailOffsetY / offsetYInitial.value))
                    .scale(scale)
            )
        }
    }
}

@Composable
fun CoinRewardCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = RewardCoinCardLighter),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WCircle()

            Spacer(modifier = Modifier.width(9.dp))

            Text(
                text = stringResource(R.string.add_twenty),
                style = TextStyle(
                    color = RewardCoinCard,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun WCircle() {
    Card(
        modifier = Modifier.size(16.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = RewardCoinCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, RewardCoinCard, CircleShape)
                .padding(1.dp)
                .border(1.dp, RewardCoinCardLighter, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.w),
                style = TextStyle(
                    color = RewardCoinCardLighter,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewWCircle() {
    WCircle()
}

@Preview
@Composable
fun PreviewCoinRewardCard() {
    CoinRewardCard(modifier = Modifier)
}