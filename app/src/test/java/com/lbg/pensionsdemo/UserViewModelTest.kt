package com.lbg.pensionsdemo

import app.cash.turbine.test
import com.lbg.pensionsdemo.data.local.entity.toUserDomain
import com.lbg.pensionsdemo.domain.usecase.IGetUserUseCase
import com.lbg.pensionsdemo.testHelper.MainDispatcherRule
import com.lbg.pensionsdemo.testHelper.UserTestData
import com.lbg.pensionsdemo.ui.greetings.GreetingsViewModel
import com.lbg.pensionsdemo.ui.model.mapToUIModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: GreetingsViewModel

    @MockK
    private lateinit var getUserUseCase: IGetUserUseCase


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = GreetingsViewModel(getUserUseCase)
    }


    @Test
    fun `getAllCharacters success`() = runTest {
        // Arrange
        val users =
            UserTestData.getUsersListFromLocal().map { it.toUserDomain() }

         coEvery { getUserUseCase() } returns users.first()

        viewModel.getUser()
        // Act
        viewModel.uiState.test {
            // Assert
            assertEquals(GreetingsViewModel.GreetingsUIState.Loading, awaitItem()) // Initial state
            assertEquals(
                GreetingsViewModel.GreetingsUIState.Success(users.first().mapToUIModel()),
                awaitItem()
            ) // Success state
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getAllCharacters error`() = runTest {
        //Arrange
        val exception = Exception("Network error")
        coEvery  { getUserUseCase() }throws exception
        viewModel.getUser()
        // Act
        viewModel.uiState.test {
            // Assert
            assertEquals(GreetingsViewModel.GreetingsUIState.Loading, awaitItem()) // Initial state
            assertEquals(GreetingsViewModel.GreetingsUIState.Error(exception), awaitItem()) // Error state
            cancelAndIgnoreRemainingEvents()
        }
    }
}