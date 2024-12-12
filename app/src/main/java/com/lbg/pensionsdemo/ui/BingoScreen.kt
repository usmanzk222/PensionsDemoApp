package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.lbg.pensionsdemo.R
import com.lbg.pensionsdemo.ui.theme.AppBarWithClose
import com.lbg.pensionsdemo.ui.theme.TickCircle
import com.lbg.pensionsdemo.ui.theme.Typography

@Composable
fun BingoScreen(
    onCloseClicked: () -> Unit,
    navigateToBingoCellScreen: () -> Unit,
    isRewardCardVisible: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (!isRewardCardVisible) {
            // Regular UI when reward card is not visible
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                AppBarWithClose(onCloseClicked = onCloseClicked)
                Spacer(modifier = Modifier.height(33.dp))
                ScreenTitle(Modifier)
                Spacer(modifier = Modifier.height(45.dp))
                BingoGrid(navigateToBingoCellScreen)
                Spacer(modifier = Modifier.height(13.dp))
                SpeechBubble()
            }
        }

        // Rewards card positioned at the very bottom
        if (isRewardCardVisible) {
            RewardsCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Align to the bottom of the screen
                // Optional padding to give some space from the edges
            )
        }
    }
}


@Composable
fun ScreenTitle(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
    ) {
        Text(
            text = stringResource(R.string.personalised_next_steps_title),
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        CoinRewardCard(isProfileReward = true, Modifier)
    }
}

@Composable
fun BingoGrid(navigateToBingoCellScreen: (() -> Unit)) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(gridItems.size) { item ->
            val gridCell = gridItems[item]
            BingoCell(
                imageId = gridCell.iconId,
                textId = gridCell.displayText,
                isCompleted = gridCell.isCompleted,
                navigateToBingoCellScreen = navigateToBingoCellScreen
            )
        }
    }
}

@Composable
fun BingoCell(
    imageId: Int,
    textId: Int,
    isCompleted: Boolean,
    navigateToBingoCellScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(118.dp)
            .height(118.dp)
            .clickable(
                onClick = navigateToBingoCellScreen
            )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = "Cell Icon",
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(textId),
                    style = Typography.bodySmall
                )
            }
        }

        if (isCompleted) {
            CompletedBingoCell(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun CompletedBingoCell(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(3.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.7f)
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TickCircle()
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.completed),
                    style = Typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun TickCircle() {
    Card(
        modifier = Modifier.size(16.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = TickCircle),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "Check Icon",
                tint = Color.White,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
fun SpeechBubble() {
    Box(
        modifier = Modifier
            .padding(start = 93.dp)
            .height(46.dp)
            .width(232.dp)
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.speech_bubblee),
                contentDescription = "Cell Icon",
            )
        }
        Text(
            text = stringResource(R.string.one_away_from_unlocking),
            style = Typography.bodySmall,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 14.dp, start = 18.dp)
        )
    }
}


@Preview
@Composable
fun BingoScreenWithBubblePreview() {
    MaterialTheme {
        SpeechBubble()
    }
}

@Preview(showBackground = false)
@Composable
fun TickCirclePreview() {
    TickCircle()
}

@Preview(showBackground = true)
@Composable
fun BingoScreenWithRewardsPreview() {
    BingoScreen({}, {}, true)
}

@Preview(showBackground = false)
@Composable
fun BingoScreenPreview() {
    BingoScreen({}, {}, false)
}

@Preview(showBackground = false)
@Composable
fun BingoCellPreview() {
    BingoCell(R.drawable.eyeing_emoji, R.string.connect_other_accounts, true, {})
}

@Preview(showBackground = false)
@Composable
fun BingoGridPreview() {
    BingoGrid({})
}