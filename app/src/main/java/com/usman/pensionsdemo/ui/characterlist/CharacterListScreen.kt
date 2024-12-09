package com.usman.pensionsdemo.ui.characterlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.usman.pensionsdemo.R
import com.usman.pensionsdemo.domain.model.House
import com.usman.pensionsdemo.ui.characterlist.CharacterViewModel.CharacterUiState
import com.usman.pensionsdemo.ui.model.CharacterUiModel

@Composable
fun CharacterListScreen(
    snackBarHostState: SnackbarHostState,
    padding: PaddingValues,
    showSearchBar: Boolean,
    searchText: String,
    onSearchTextChanged: (String) -> Unit, // Callback to update search text
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    val characterUiState: CharacterUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(padding)) {
        if (showSearchBar) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        // ... UI to display character list based on uiState
        when (val uiState = characterUiState) {
            is CharacterUiState.Success -> {
                val filteredCharacters = if (searchText.isBlank()) {
                    uiState.characters
                } else {
                    uiState.characters.filter {
                        it.name.contains(searchText, ignoreCase = true) || it.actor.contains(searchText, ignoreCase = true)
                    }
                }

                CharactersList(filteredCharacters, onCharacterClick)
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
                        .fillMaxSize()
                ) {
                    items(10) {
                        CharacterItemPlaceholder()
                    }
                }
            }
        }
    }
}

@Composable
fun CharactersList(
    characters: List<CharacterUiModel>,
    onCharacterClick: (CharacterUiModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(characters) { character ->
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

    CharactersList(charactersList) {

    }
}

