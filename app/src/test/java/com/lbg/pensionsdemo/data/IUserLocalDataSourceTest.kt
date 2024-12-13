package com.lbg.pensionsdemo.data

import com.lbg.pensionsdemo.data.local.IUserLocalDataSource
import com.lbg.pensionsdemo.data.local.UserLocalDataSource
import com.lbg.pensionsdemo.data.local.database.PensionsDao
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import com.lbg.pensionsdemo.data.remote.model.toUserEntity
import com.lbg.pensionsdemo.testHelper.UserTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class IUserLocalDataSourceTest {

    private val characterDao = mockk<PensionsDao>()
    private lateinit var characterLocalDataSource: IUserLocalDataSource

    @Before
    fun setup() {
        characterLocalDataSource = UserLocalDataSource(characterDao)
    }

    @Test
    fun `getAllCharacters returns all characters from the database`() = runTest {
        // Insert some test characters
        val mockCharacters: List<UserEntity> = (0..5).map (UserTestData::getUser).map { it.toUserEntity() }
        every { characterDao.getAllCharacters() } returns flowOf(mockCharacters)

        // Get characters from the data source
        val characters = characterLocalDataSource.getUser()

        // Verify the result
        assertEquals(mockCharacters, characters)
    }

    @Test
    fun `getCharacterById returns the correct character for a given ID`() = runTest {
        // Insert a test character
        val testCharacter = UserTestData.getUser(0).toUserEntity()
        coEvery { characterDao.getCharacterById(testCharacter.id) } returns testCharacter

        // Get the character by ID
        val character = characterLocalDataSource.getUser()

        // Verify the result
        assertNotNull(character)
        assertEquals(testCharacter, character)
    }

}