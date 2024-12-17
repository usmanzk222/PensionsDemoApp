package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lbg.pensionsdemo.R

@Composable
fun LandingPage(paddingValues: PaddingValues, navigateToBingoScreen: () -> Unit) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {

        val (image, bottomView, coinsCounterView) = createRefs()
        val guideline = createGuidelineFromBottom(0.15f) // 15% from bottom

        Image(
            painter = painterResource(id = R.drawable.home_screen),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            contentScale = ContentScale.Crop
        )

        // Coins counter on top right. Remove this box & coinsCounterView line 27 if we don't need click on this view
        Box(
            modifier = Modifier
                .width(124.dp)
                .height(55.dp)
                .constrainAs(coinsCounterView) {
                    top.linkTo(parent.top, margin = 40.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                }
                .clickable(
                    onClick = navigateToBingoScreen
                )
        )


        // One pot that rules all. Remove this box & bottomView on line 27 if we don't need click on the view that says one pot rules all
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .constrainAs(bottomView) {
                    bottom.linkTo(guideline) // Link to the guideline
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable(
                    onClick = navigateToBingoScreen
                )
        )
    }

}


@Preview
@Composable
fun LandingPreview() {
    LandingPage(PaddingValues(16.dp)) {}
}