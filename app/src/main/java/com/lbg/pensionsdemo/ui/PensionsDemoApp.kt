package com.lbg.pensionsdemo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import java.util.Date

@Composable
fun PensionsDemoApp(navigationTarget: String?, navController: NavHostController = rememberNavController()) {
    val snackBarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val navigationTargetState by remember { mutableStateOf(navigationTarget) }

    val currentScreen: AppScreens =
        AppScreens.entries.find { it.route == backStackEntry?.destination?.route }
            ?: AppScreens.LANDING

    val canNavigateBack = currentScreen != AppScreens.LANDING

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = AppScreens.LANDING.route) {
            composable(AppScreens.LANDING.route) {
              //  LandingPage(paddingValues)
                BingoScreen(onCloseClicked = {}, navigateToBingoCellScreen = {}, false)
            }

            navigation( route = AppScreens.GREETINGS.route, startDestination = AppScreens.GREETINGS_HOME.route){
                composable(AppScreens.GREETINGS_HOME.route) {
                    GreetingsHome(
                        customerName = "Sarah",
                        customerBirthdate = Date(1998 - 1900, 0, 1),
                        navigateToLostPensionsScreen = { }
                    )
                }
            }

            navigation( route = AppScreens.BINGO.route, startDestination = AppScreens.BINGO_HOME.route){
                composable(AppScreens.BINGO_HOME.route) {
                    BingoScreen(onCloseClicked = {}, navigateToBingoCellScreen = {},
                        isRewardCardVisible = true
                    )
                }
            }
        }

    }

    LaunchedEffect(navigationTargetState) {
        navigationTargetState?.let { target ->
            if(AppScreens.entries.any { it.route == target })
                navController.navigate(target)
        }
    }

}
