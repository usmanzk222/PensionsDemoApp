package com.lbg.pensionsdemo.ui.greetings

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lbg.pensionsdemo.R
import com.lbg.pensionsdemo.ui.greetings.GreetingsViewModel.GreetingsUIState
import com.lbg.pensionsdemo.ui.model.UserUiModel
import com.lbg.pensionsdemo.ui.theme.SWButton
import com.lbg.pensionsdemo.ui.theme.SWLinkButton

@Composable
fun GreetingsHome(
    snackBarHostState: SnackbarHostState,
    padding: PaddingValues,
    viewModel: GreetingsViewModel = hiltViewModel(),
    navigateToLostPensionsScreen: () ->Unit,
    navigateToHomeScreen: () -> Unit,
) {
    val greetingsUIState: GreetingsUIState by viewModel.uiState.collectAsStateWithLifecycle()
    when (greetingsUIState) {
        is GreetingsUIState.Success -> {
            val user = (greetingsUIState as GreetingsUIState.Success).user
            GreetingsHomeContent(user, padding, navigateToLostPensionsScreen, navigateToHomeScreen)

        }

        is GreetingsUIState.Error -> {
            val message = (greetingsUIState as GreetingsUIState.Error).exception.message
            LaunchedEffect(Unit) {
                val result = snackBarHostState.showSnackbar(
                    message = "Error: $message",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite // Keep Snackbar visible until dismissed
                )
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.getUser()// Retry fetch on Snackbar action
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(shimmerBrush())
            )
        }
    }
}

@Composable
fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "transition")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "translateAnim"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim, translateAnim),
        end = Offset(translateAnim + 1000f, translateAnim + 1000f)
    )
}

@Composable
fun GreetingsHomeContent(
    user: UserUiModel,
    padding: PaddingValues,
    navigateToLostPensionsScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        CakeAnimation(modifier = Modifier.fillMaxWidth())
        BirthdayMessage(
            ageString = user.ordinalAge,
            name = user.name
        )
        Spacer(modifier = Modifier.weight(1f))
        SWButton(
            onClick = navigateToLostPensionsScreen,
            buttonText = stringResource(R.string.see_how_we_can_help),
            modifier = Modifier.padding(top = 32.dp)
        )
        SWLinkButton(
            onClick = navigateToHomeScreen,
            buttonText = stringResource(R.string.not_now),
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
        progress = { preloaderProgress },
        modifier = modifier
            .height(300.dp)
    )
}

@Composable
fun BirthdayMessage(
    ageString: String,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.birthday_message_heading, ageString, name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.birthday_message_body),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LandingPagePreview() {
    GreetingsHomeContent(
        UserUiModel(1, "Usman", "usman@gmail.com", "22/02/1991", 34, "34th",2),
        padding = PaddingValues(24.dp),
        navigateToLostPensionsScreen = { },
        navigateToHomeScreen = { },
    )
}