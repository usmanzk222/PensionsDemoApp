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
import com.lbg.pensionsdemo.ui.greetings.GreetingsHome

@Composable
fun PensionsDemoApp(
    navigationTarget: String?,
    navController: NavHostController = rememberNavController()
) {
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
                LandingPage(paddingValues = paddingValues) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = BINGO_NAV_DATA_KEY,
                        value = false
                    )
                    navController.navigate(AppScreens.BINGO.route)
                }
            }

            navigation(
                route = AppScreens.GREETINGS.route,
                startDestination = AppScreens.GREETINGS_HOME.route
            ) {
                composable(AppScreens.GREETINGS_HOME.route) {
                    GreetingsHome(
                        snackBarHostState,
                        padding = paddingValues,
                        navigateToLostPensionsScreen = { }
                    ) {
                        navController.navigateToHome(true)
                    }
                }
            }

            navigation(
                route = AppScreens.BINGO.route,
                startDestination = AppScreens.BINGO_HOME.route
            ) {
                composable(AppScreens.BINGO_HOME.route) {
                    val isRewardCardVisible =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Boolean>(
                            BINGO_NAV_DATA_KEY
                        ) ?: true

                    BingoScreen(
                        isRewardCardVisible = isRewardCardVisible,
                        navigateToBingoCellScreen = {}) {
                        navController.navigateToHome(true)
                    }
                }
            }
        }

        LaunchedEffect(navigationTargetState) {
            navigationTargetState?.let { target ->
                if (AppScreens.entries.any { it.route == target })
                    navController.navigate(target)
            }
        }
    }
}

fun NavHostController.navigateToHome(popBackStack: Boolean) {
    navigate(AppScreens.LANDING.route) {
        popUpTo(AppScreens.LANDING.route) {
            inclusive = popBackStack
        }
    }
}
