package com.usman.pensionsdemo.data

import com.usman.pensionsdemo.data.remote.PensionsRemoteDataSource
import com.usman.pensionsdemo.data.remote.HpApiService
import com.usman.pensionsdemo.data.remote.IPensionsRemoteDataSource
import com.usman.pensionsdemo.testHelper.CharactersTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class IPensionsRemoteDataSourceTest {

    private lateinit var characterService: HpApiService
    private lateinit var characterRemoteDataSource: IPensionsRemoteDataSource

    @Before
    fun setup() {
        characterService = mockk()
        characterRemoteDataSource = PensionsRemoteDataSource(characterService)
    }

    @Test
    fun `getAllCharacters returns success result when API call is successful`() = runTest {
        // Mock API response
        val mockCharacters = (0..5).map (CharactersTestData::getCharacter)
        coEvery { characterService.getPensions() } returns mockCharacters

        // Call the function
        val result = characterRemoteDataSource.getPensions()

        // Verify the result
        assertTrue(result.isSuccess)
        assertEquals(mockCharacters, result.getOrNull())
        coVerify(exactly = 1) { characterService.getPensions() }
    }

    @Test
    fun `getAllCharacters returns failure result when API call throws exception`() = runTest {
        // Mock API exception
        val exception = Exception("Network error")
        coEvery { characterService.getPensions() } throws exception

        // Call the function
        val result = characterRemoteDataSource.getPensions()

        // Verify the result
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { characterService.getPensions() }
    }
}