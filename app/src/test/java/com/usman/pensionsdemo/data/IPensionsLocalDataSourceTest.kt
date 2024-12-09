package com.usman.pensionsdemo.data

import com.usman.pensionsdemo.data.local.IPensionsLocalDataSource
import com.usman.pensionsdemo.data.local.PensionsLocalDataSource
import com.usman.pensionsdemo.data.local.database.PensionsDao
import com.usman.pensionsdemo.data.local.entity.CharacterEntity
import com.usman.pensionsdemo.data.remote.model.toCharacterEntity
import com.usman.pensionsdemo.testHelper.CharactersTestData
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

class IPensionsLocalDataSourceTest {

    private val characterDao = mockk<PensionsDao>()
    private lateinit var characterLocalDataSource: IPensionsLocalDataSource

    @Before
    fun setup() {
        characterLocalDataSource = PensionsLocalDataSource(characterDao)
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