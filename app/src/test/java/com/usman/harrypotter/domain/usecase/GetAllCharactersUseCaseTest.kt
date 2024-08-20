package com.usman.harrypotter.domain.usecase

import app.cash.turbine.test
import com.usman.harrypotter.data.local.entity.toCharacterDomain
import com.usman.harrypotter.data.repository.ICharacterRepository
import com.usman.harrypotter.testHelper.CharactersTestData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetAllCharactersUseCaseTest {

    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase
    private val mockCharacterRepository = mockk<ICharacterRepository>()

    @Before
    fun setup() {
        getAllCharactersUseCase = GetAllCharactersUseCase(mockCharacterRepository)
    }

    @Test
    fun `invoke returns flow of characters from repository`() = runBlocking {
        // Arrange
        val characters = CharactersTestData.getCharactersListFromLocal().map { it.toCharacterDomain() }
        every { mockCharacterRepository.getAllCharacters() } returns flowOf(
            Result.success(
                characters
            )
        )

        // Act
        getAllCharactersUseCase().test {
            // Assert
            assertEquals(Result.success(characters), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
