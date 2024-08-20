package com.usman.harrypotter.data

import com.usman.harrypotter.data.local.ICharacterLocalDataSource
import com.usman.harrypotter.data.local.CharacterLocalDataSource
import com.usman.harrypotter.data.local.database.CharacterDao
import com.usman.harrypotter.data.local.entity.CharacterEntity
import com.usman.harrypotter.data.remote.model.toCharacterEntity
import com.usman.harrypotter.testHelper.CharactersTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ICharacterLocalDataSourceTest {

    private val characterDao = mockk<CharacterDao>()
    private lateinit var characterLocalDataSource: ICharacterLocalDataSource

    @Before
    fun setup() {
        characterLocalDataSource = CharacterLocalDataSource(characterDao)
    }

    @Test
    fun `getAllCharacters returns all characters from the database`() = runTest {
        // Insert some test characters
        val mockCharacters: List<CharacterEntity> = (0..5).map (CharactersTestData::getCharacter).map { it.toCharacterEntity() }
        every { characterDao.getAllCharacters() } returns flowOf(mockCharacters)

        // Get characters from the data source
        val characters = characterLocalDataSource.getAllCharacters().first()

        // Verify the result
        assertEquals(mockCharacters, characters)
    }

    @Test
    fun `getCharacterById returns the correct character for a given ID`() = runTest {
        // Insert a test character
        val testCharacter = CharactersTestData.getCharacter(0).toCharacterEntity()
        coEvery { characterDao.getCharacterById(testCharacter.id) } returns testCharacter

        // Get the character by ID
        val character = characterLocalDataSource.getCharacterById(testCharacter.id)

        // Verify the result
        assertNotNull(character)
        assertEquals(testCharacter, character)
    }

}