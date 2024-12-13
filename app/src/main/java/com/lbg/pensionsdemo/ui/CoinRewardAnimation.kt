package com.lbg.pensionsdemo.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
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
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    // Start within the box
    var offsetY by remember { mutableStateOf(0.dp) }
    val trailCards = remember { mutableStateListOf<Float>() }

    LaunchedEffect(key1 = true) {
        delay(1000)
        animationStarted = true

        // Animate within the box
        for (i in 0..5) {
            offsetY = if (i % 2 == 0) (-20).dp else 0.dp
            trailCards.add(offsetY.value)
            delay(200)
            trailCards.removeAll { it != offsetY.value }
        }

        // Final position
        offsetY = 0.dp
        trailCards.clear()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .size(250.dp)
            .clipToBounds()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        CoinRewardCard(
            isProfileReward = false,
            modifier = Modifier
                .offset(y = offsetY)
                .scale(scale)
        )

        trailCards.forEach { trailOffsetY ->
            CoinRewardCard(
                isProfileReward = false,
                modifier = Modifier
                    .offset(y = trailOffsetY.dp)
                    .alpha(if (trailOffsetY != 0f) 0.5f else 1f)
                    .scale(scale)
            )
        }
    }
}

@Composable
fun CoinRewardCard(isProfileReward: Boolean, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(containerColor = RewardCoinCardLighter),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp),
            ) {
                WCircle()
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    text = stringResource(
                        if (isProfileReward) R.string.user_points else R.string.add_twenty
                    ),
                    style = TextStyle(
                        color = RewardCoinCard,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.width(9.dp))
            if (isProfileReward) {
                UserProfileCircle()
            }
        }
    }
}

@Composable
fun UserProfileCircle() {
    Card(
        modifier = Modifier.size(26.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = RewardCoinCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.profile_vector),
                contentDescription = "Profile Image",
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
    CoinRewardCard(isProfileReward = false, modifier = Modifier)
}

@Preview
@Composable
fun PreviewCoinRewardCardWithProfile() {
    CoinRewardCard(isProfileReward = true, modifier = Modifier)
}