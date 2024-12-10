package com.lbg.pensionsdemo.data

import android.util.Log
import app.cash.turbine.test
import com.lbg.pensionsdemo.data.local.IPensionsLocalDataSource
import com.lbg.pensionsdemo.data.local.entity.CharacterEntity
import com.lbg.pensionsdemo.data.local.entity.toCharacterDomain
import com.lbg.pensionsdemo.data.remote.IPensionsRemoteDataSource
import com.lbg.pensionsdemo.data.remote.model.Character
import com.lbg.pensionsdemo.data.remote.model.toCharacterEntity
import com.lbg.pensionsdemo.data.repository.PensionsRepository
import com.lbg.pensionsdemo.data.repository.IPensionsRepository
import com.lbg.pensionsdemo.testHelper.CharactersTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class PensionsRepositoryTest {

    private lateinit var characterRepository: IPensionsRepository
    private val mockCharacterLocalDataSource = mockk<IPensionsLocalDataSource>()
    private val mockCharacterRemoteDataSource = mockk<IPensionsRemoteDataSource>()

    @Before
    fun setup() {
        characterRepository = PensionsRepository(mockCharacterLocalDataSource, mockCharacterRemoteDataSource)
    }

    @Test
    fun `getAllCharacters returns local data and fetches remote data`() = runTest {
        // Arrange
        val localCharacters: List<CharacterEntity> = CharactersTestData.getCharactersListFromLocal(1 .. 7)
        val remoteCharacters: List<Character> = CharactersTestData.getCharactersListFromRemote(1 .. 3)

        every { mockCharacterLocalDataSource.getAllCharacters() } returns flowOf(localCharacters)
        coEvery { mockCharacterRemoteDataSource.getPensions() } returns Result.success(remoteCharacters)
        coEvery { mockCharacterLocalDataSource.insertAllCharacters(any()) } returns Unit

        // Act
        characterRepository.getPensions().test {
            // Assert
            assertEquals(Result.success(localCharacters.map { it.toCharacterDomain() }), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getAllCharacters emits local date when remote data fetch fails`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { mockCharacterRemoteDataSource.getPensions() } returns Result.failure(exception)
        val localCharacters: List<CharacterEntity> = CharactersTestData.getCharactersListFromLocal()
        every { mockCharacterLocalDataSource.getAllCharacters() } returns flowOf(localCharacters)

        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        // Act
        characterRepository.getPensions().test {
            // Assert
            verify {  Log.e(any(), any())  }
            assertEquals(Result.success(localCharacters.map { it.toCharacterDomain() }), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCharacterById returns character from local data source`() = runTest {
        // Arrange
        val characterId = "1"
        val characterEntity = CharactersTestData.getCharacter(1).toCharacterEntity()
        coEvery { mockCharacterLocalDataSource.getCharacterById(characterId) } returns characterEntity

        // Act
        val result = characterRepository.getPensionsById(characterId)

        // Assert
        assertEquals(characterEntity.toCharacterDomain(), result)
    }
}