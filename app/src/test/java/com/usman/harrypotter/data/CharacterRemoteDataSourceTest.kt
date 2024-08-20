package com.usman.harrypotter.data

import com.usman.harrypotter.data.remote.CharacterRemoteDataSource
import com.usman.harrypotter.data.remote.HpApiService
import com.usman.harrypotter.data.remote.ICharacterRemoteDataSource
import com.usman.harrypotter.testHelper.CharactersTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class ICharacterRemoteDataSourceTest {

    private lateinit var characterService: HpApiService
    private lateinit var characterRemoteDataSource: ICharacterRemoteDataSource

    @Before
    fun setup() {
        characterService = mockk()
        characterRemoteDataSource = CharacterRemoteDataSource(characterService)
    }

    @Test
    fun `getAllCharacters returns success result when API call is successful`() = runTest {
        // Mock API response
        val mockCharacters = (0..5).map (CharactersTestData::getCharacter)
        coEvery { characterService.getCharacters() } returns mockCharacters

        // Call the function
        val result = characterRemoteDataSource.getAllCharacters()

        // Verify the result
        assertTrue(result.isSuccess)
        assertEquals(mockCharacters, result.getOrNull())
        coVerify(exactly = 1) { characterService.getCharacters() }
    }

    @Test
    fun `getAllCharacters returns failure result when API call throws exception`() = runTest {
        // Mock API exception
        val exception = Exception("Network error")
        coEvery { characterService.getCharacters() } throws exception

        // Call the function
        val result = characterRemoteDataSource.getAllCharacters()

        // Verify the result
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { characterService.getCharacters() }
    }
}