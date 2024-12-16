package com.lbg.pensionsdemo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
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

//todo underline button text
@Composable
fun SWLinkButton(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = PrimaryButtonContainer,
            style = TextStyle(textDecoration = TextDecoration.Underline)
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
        progress = { preloaderProgress },
        modifier = modifier
            .height(height.dp)
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBackground
        ),
        modifier = Modifier.fillMaxWidth()
            .background(AppBackground)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSWLinkButton() {
    SWLinkButton(
        onClick = { },
        buttonText = stringResource(R.string.not_now),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppBar() {
    AppBarWithClose {}
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
