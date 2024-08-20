package com.usman.harrypotter.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.usman.harrypotter.R
import com.usman.harrypotter.ui.characterdetail.CharacterDetailScreen
import com.usman.harrypotter.ui.characterdetail.DETAIL_NAV_KEY
import com.usman.harrypotter.ui.characterlist.CharacterListScreen
import com.usman.harrypotter.ui.model.CharacterUiModel

@Composable
fun HarryPorterCharactersApp(navController: NavHostController = rememberNavController()) {
    val snackBarHostState = remember { SnackbarHostState() }
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen: AppScreens =
        AppScreens.entries.find { it.route == backStackEntry?.destination?.route }
            ?: AppScreens.HOME

    val canNavigateBack = currentScreen != AppScreens.HOME
    var characterName by remember { mutableStateOf<String>("") }
    val title = if(currentScreen == AppScreens.DETAIL) characterName else stringResource(currentScreen.title)

    Scaffold(
        topBar = {
            CharacterTopAppBar(
                title = title,
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = AppScreens.HOME.route) {
            composable(AppScreens.HOME.route) {
                CharacterListScreen(snackBarHostState, innerPadding) { character ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = DETAIL_NAV_KEY,
                        value = character
                    )
                    characterName = character.name
                    navController.navigate(AppScreens.DETAIL.route)
                }
            }
            composable(AppScreens.DETAIL.route) {
                val character =
                    navController.previousBackStackEntry?.savedStateHandle?.get<CharacterUiModel>(
                        DETAIL_NAV_KEY
                    )
                character?.let { CharacterDetailScreen(it, innerPadding) }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_back_button)
                    )
                }
            }
        }
    )
}
