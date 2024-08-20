package com.usman.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.usman.harrypotter.ui.HarryPorterCharactersApp
import com.usman.harrypotter.ui.theme.HarryPotterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HarryPotterAppTheme {
                HarryPorterCharactersApp()
            }
        }
    }
}
