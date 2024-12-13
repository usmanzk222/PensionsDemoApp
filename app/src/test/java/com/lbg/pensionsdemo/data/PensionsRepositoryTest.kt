package com.lbg.pensionsdemo.data

import android.util.Log
import com.lbg.pensionsdemo.data.local.IUserLocalDataSource
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import com.lbg.pensionsdemo.data.local.entity.toUserDomain
import com.lbg.pensionsdemo.data.remote.IUserRemoteDataSource
import com.lbg.pensionsdemo.data.remote.model.User
import com.lbg.pensionsdemo.data.remote.model.UserResponse
import com.lbg.pensionsdemo.data.remote.model.toUserEntity
import com.lbg.pensionsdemo.data.repository.IUsersRepository
import com.lbg.pensionsdemo.data.repository.UsersRepository
import com.lbg.pensionsdemo.testHelper.UserTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class PensionsRepositoryTest {

    private lateinit var characterRepository: IUsersRepository
    private val mockCharacterLocalDataSource = mockk<IUserLocalDataSource>()
    private val mockCharacterRemoteDataSource = mockk<IUserRemoteDataSource>()

    @Before
    fun setup() {
        characterRepository =
            UsersRepository(mockCharacterLocalDataSource, mockCharacterRemoteDataSource)
    }

    @Test
    fun `getAllCharacters returns local data and fetches remote data`() = runTest {
        // Arrange
        val localCharacters: List<UserEntity> = UserTestData.getUsersListFromLocal(1..7)
        val remoteCharacters: List<User> = UserTestData.getCharactersListFromRemote(1..3)

        coEvery { mockCharacterLocalDataSource.getUser() } returns localCharacters.first()

        val response =  UserResponse(success = true, remoteCharacters.first(), message = "Success!")
        coEvery { mockCharacterRemoteDataSource.getUser() } returns Result.success(response)
        coEvery { mockCharacterLocalDataSource.insertUser(any()) } returns Unit

        // Act
        val result = characterRepository.getUser()
        // Assert
        assertEquals(Result.success(localCharacters.map { it.toUserDomain() }),result)


    }

    @Test
    fun `getAllCharacters emits local date when remote data fetch fails`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        coEvery { mockCharacterRemoteDataSource.getUser() } returns Result.failure(exception)
        val localCharacters: List<UserEntity> = UserTestData.getUsersListFromLocal()
        coEvery { mockCharacterLocalDataSource.getUser() } returns localCharacters.first()

        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        // Act
        val result = characterRepository.getUser()
        // Assert
        verify { Log.e(any(), any()) }
        assertEquals(Result.success(localCharacters.map { it.toUserDomain() }), result)

    }

    @Test
    fun `getCharacterById returns character from local data source`() = runTest {
        // Arrange
        val characterId = "1"
        val characterEntity = UserTestData.getUser(1).toUserEntity()
        coEvery { mockCharacterLocalDataSource.getUser() } returns characterEntity

        // Act
        val result = characterRepository.getUser()

        // Assert
        assertEquals(characterEntity.toUserDomain(), result)
    }
}