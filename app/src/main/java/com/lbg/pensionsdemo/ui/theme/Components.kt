package com.lbg.pensionsdemo.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lbg.pensionsdemo.R

@Composable
fun SWButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryButtonContainer,
            contentColor = PrimaryButtonText
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = Color(0xFFFFFFFF),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Composable
fun LoadLottieAnimation(
    height: Int,
    topPadding: Int,
    animationId: Int,
    modifier: Modifier
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            animationId
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier.height(height.dp)
            .padding(top = topPadding.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithClose(onCloseClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "")
        },
        actions = {
            IconButton(onClick = { onCloseClicked() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppBar() {
    AppBarWithClose ({})
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadLottieAnimation() {
    LoadLottieAnimation(
        400,
        100,
        R.raw.birthday_hat,
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    SWButton(
        onClick = {},
        buttonText = "Do you have lost pensions",
        modifier = Modifier
    )
}
