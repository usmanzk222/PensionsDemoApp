package com.usman.pensionsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.usman.pensionsdemo.ui.LandingPage
import com.usman.pensionsdemo.ui.theme.PensionsDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PensionsDemoAppTheme {
                LandingPage(
                    navigateToLostPensionsScreen = { },
                    customerName = "Sarah",
                    customerBirthdate = Date(1998 - 1900, 0, 1)
                )
            }
        }
    }
}