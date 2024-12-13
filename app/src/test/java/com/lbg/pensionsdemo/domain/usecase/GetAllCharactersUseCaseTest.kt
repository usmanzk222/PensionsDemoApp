package com.lbg.pensionsdemo.domain.usecase

import com.lbg.pensionsdemo.data.local.entity.toUserDomain
import com.lbg.pensionsdemo.data.repository.IUsersRepository
import com.lbg.pensionsdemo.testHelper.UserTestData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetAllCharactersUseCaseTest {

    private lateinit var getAllCharactersUseCase: GetUserUseCase
    private val mockCharacterRepository = mockk<IUsersRepository>()

    @Before
    fun setup() {
        getAllCharactersUseCase = GetUserUseCase(mockCharacterRepository)
    }

    @Test
    fun `invoke returns flow of characters from repository`() = runBlocking {
        // Arrange
        val characters = UserTestData.getUsersListFromLocal().map { it.toUserDomain() }
        coEvery { mockCharacterRepository.getUser() } returns characters.first()


        // Act
        val result = getAllCharactersUseCase()

        // Assert
        assertEquals(Result.success(characters), result)
    }
}
