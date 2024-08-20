package com.usman.harrypotter

import app.cash.turbine.test
import com.usman.harrypotter.data.local.entity.toCharacterDomain
import com.usman.harrypotter.domain.usecase.IGetAllCharactersUseCase
import com.usman.harrypotter.testHelper.CharactersTestData
import com.usman.harrypotter.testHelper.MainDispatcherRule
import com.usman.harrypotter.ui.characterlist.CharacterViewModel
import com.usman.harrypotter.ui.characterlist.CharacterViewModel.CharacterUiState
import com.usman.harrypotter.ui.model.mapToUIModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: CharacterViewModel

    @MockK
    private lateinit var getAllCharactersUseCase: IGetAllCharactersUseCase


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = CharacterViewModel(getAllCharactersUseCase)
    }


    @Test
    fun `getAllCharacters success`() = runTest {
        // Arrange
        val characters =
            CharactersTestData.getCharactersListFromLocal().map { it.toCharacterDomain() }

         coEvery { getAllCharactersUseCase() } returns flowOf(Result.success(characters))

        viewModel.getAllCharacters()
        // Act
        viewModel.uiState.test {
            // Assert
            assertEquals(CharacterUiState.Loading, awaitItem()) // Initial state
            assertEquals(
                CharacterUiState.Success(characters.map { it.mapToUIModel() }),
                awaitItem()
            ) // Success state
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getAllCharacters error`() = runTest {
        //Arrange
        val exception = Exception("Network error")
        every { getAllCharactersUseCase() } returns flowOf(Result.failure(exception))
        viewModel.getAllCharacters()
        // Act
        viewModel.uiState.test {
            // Assert
            assertEquals(CharacterUiState.Loading, awaitItem()) // Initial state
            assertEquals(CharacterUiState.Error(exception), awaitItem()) // Error state
            cancelAndIgnoreRemainingEvents()
        }
    }
}