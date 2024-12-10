package com.usman.pensionsdemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.usman.pensionsdemo.R
import com.usman.pensionsdemo.ui.theme.SWButton
import com.usman.pensionsdemo.ui.theme.calculateAge
import java.util.Date

@Composable
fun LandingPage(
    navigateToLostPensionsScreen: () -> Unit,
    customerName: String,
    customerBirthdate: Date,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CakeAnimation(modifier = Modifier.fillMaxWidth())
        BirthdayMessage(
            ageString = calculateAge(
                birthDate = customerBirthdate
            ),
            name = customerName,
            modifier = Modifier,
            onClick = navigateToLostPensionsScreen
        )
    }
}

@Composable
fun CakeAnimation(modifier: Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.birthday_hat
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier.height(400.dp)
            .padding(top = 100.dp)
    )
}

@Composable
fun BirthdayMessage(ageString: String,  name: String, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(34.dp))
        Text(
            text = stringResource(R.string.birthday_message_heading, ageString, name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.birthday_message_body),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        SWButton(
            onClick = { onClick },
            buttonText = stringResource(R.string.lost_pensions_button),
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    LandingPage(
        navigateToLostPensionsScreen = { },
        customerName = "Sarah",
        customerBirthdate = TODO()
    )
}