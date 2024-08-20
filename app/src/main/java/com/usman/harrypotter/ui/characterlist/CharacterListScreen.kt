package com.usman.harrypotter.ui.characterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.usman.harrypotter.R
import com.usman.harrypotter.domain.model.House
import com.usman.harrypotter.ui.characterlist.CharacterViewModel.CharacterUiState
import com.usman.harrypotter.ui.model.CharacterUiModel

@Composable
fun CharacterListScreen(
    snackBarHostState: SnackbarHostState,
    padding: PaddingValues,
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    val characterUiState: CharacterUiState by viewModel.uiState.collectAsStateWithLifecycle()
    // ... UI to display character list based on uiState
    when (val uiState = characterUiState) {
        is CharacterUiState.Success -> {
            CharactersList(uiState, Modifier.padding(padding), onCharacterClick)
        }

        is CharacterUiState.Error -> {
            LaunchedEffect(Unit) {
                val result = snackBarHostState.showSnackbar(
                    message = "Error: ${uiState.exception.message}",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite // Keep Snackbar visible until dismissed
                )
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.getAllCharacters()// Retry fetch on Snackbar action
                }
            }

        }

        is CharacterUiState.Loading -> {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(10) {
                    CharacterItemPlaceholder()
                }
            }
        }
    }
}

@Composable
fun CharactersList(
    uiState: CharacterUiState.Success,
    modifier: Modifier,
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(uiState.characters) { character ->
            CharacterItem(character) {
                onCharacterClick(character)
            }
        }
    }
}

@Composable
fun CharacterItemPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.item_placeholder_size))
            .padding(dimensionResource(id = R.dimen.padding_small))
            .placeholder(
                visible = true,
                color = Color.LightGray,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_corners)),
                highlight = PlaceholderHighlight.shimmer()
            )
    )
}

@Preview(showBackground = true)
@Composable
fun CharacterItemPlaceholderPreview() {
    CharacterItemPlaceholder()
}

@Preview(showBackground = true)
@Composable
fun CharactersListPreview() {
    val charactersList = (0..4).map { index ->
        CharacterUiModel(
            id = "$index",
            name = "Name $index",
            actor = "Actor $index",
            species = "Species $index",
            house = House.entries[index % 5],
            alive = "Yes",
            dateOfBirth = "19-${index % 12 + 1}-1979",
            image = "https://example.com/hermione.jpg"
        )
    }
    val uiState = CharacterUiState.Success(charactersList)
    CharactersList(uiState, Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {

    }
}

