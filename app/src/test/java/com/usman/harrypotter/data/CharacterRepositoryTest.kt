package com.usman.harrypotter.data

import android.util.Log
import app.cash.turbine.test
import com.usman.harrypotter.data.local.ICharacterLocalDataSource
import com.usman.harrypotter.data.local.entity.CharacterEntity
import com.usman.harrypotter.data.local.entity.toCharacterDomain
import com.usman.harrypotter.data.remote.ICharacterRemoteDataSource
import com.usman.harrypotter.data.remote.model.Character
import com.usman.harrypotter.data.remote.model.toCharacterEntity
import com.usman.harrypotter.data.repository.CharacterRepository
import com.usman.harrypotter.data.repository.ICharacterRepository
import com.usman.harrypotter.testHelper.CharactersTestData
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


class CharacterRepositoryTest {

    private lateinit var characterRepository: ICharacterRepository
    private val mockCharacterLocalDataSource = mockk<ICharacterLocalDataSource>()
    private val mockCharacterRemoteDataSource = mockk<ICharacterRemoteDataSource>()

    @Before
    fun setup() {
        characterRepository = CharacterRepository(mockCharacterLocalDataSource, mockCharacterRemoteDataSource)
    }

    @Test
    fun `getAllCharacters returns local data and fetches remote data`() = runTest {
        // Arrange
        val localCharacters: List<CharacterEntity> = CharactersTestData.getCharactersListFromLocal(1 .. 7)
        val remoteCharacters: List<Character> = CharactersTestData.getCharactersListFromRemote(1 .. 3)

        every { mockCharacterLocalDataSource.getAllCharacters() } returns flowOf(localCharacters)
        coEvery { mockCharacterRemoteDataSource.getAllCharacters() } returns Result.success(remoteCharacters)
        coEvery { mockCharacterLocalDataSource.insertAllCharacters(any()) } returns Unit

        // Act
        characterRepository.getAllCharacters().test {
            // Assert
            assertEquals(Result.success(localCharacters.map { it.toCharacterDomain() }), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getAllCharacters emits local date when remote data fetch fails`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { mockCharacterRemoteDataSource.getAllCharacters() } returns Result.failure(exception)
        val localCharacters: List<CharacterEntity> = CharactersTestData.getCharactersListFromLocal()
        every { mockCharacterLocalDataSource.getAllCharacters() } returns flowOf(localCharacters)

        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        // Act
        characterRepository.getAllCharacters().test {
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
        val result = characterRepository.getCharacterById(characterId)

        // Assert
        assertEquals(characterEntity.toCharacterDomain(), result)
    }
}