package com.usman.pensionsdemo.domain.usecase

import app.cash.turbine.test
import com.usman.pensionsdemo.data.local.entity.toCharacterDomain
import com.usman.pensionsdemo.data.repository.IPensionsRepository
import com.usman.pensionsdemo.testHelper.CharactersTestData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetAllCharactersUseCaseTest {

    private lateinit var getAllCharactersUseCase: PensionsUseCase
    private val mockCharacterRepository = mockk<IPensionsRepository>()

    @Before
    fun setup() {
        getAllCharactersUseCase = PensionsUseCase(mockCharacterRepository)
    }

    @Test
    fun `invoke returns flow of characters from repository`() = runBlocking {
        // Arrange
        val characters = CharactersTestData.getCharactersListFromLocal().map { it.toCharacterDomain() }
        every { mockCharacterRepository.getPensions() } returns flowOf(
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
