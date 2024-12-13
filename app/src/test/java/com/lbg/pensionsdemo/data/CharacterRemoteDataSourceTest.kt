package com.lbg.pensionsdemo.data

import com.lbg.pensionsdemo.data.remote.UserRemoteDataSource
import com.lbg.pensionsdemo.data.remote.HpApiService
import com.lbg.pensionsdemo.data.remote.IUserRemoteDataSource
import com.lbg.pensionsdemo.data.remote.model.UserResponse
import com.lbg.pensionsdemo.testHelper.UserTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class IUserRemoteDataSourceTest {

    private lateinit var characterService: HpApiService
    private lateinit var characterRemoteDataSource: IUserRemoteDataSource

    @Before
    fun setup() {
        characterService = mockk()
        characterRemoteDataSource = UserRemoteDataSource(characterService)
    }

    @Test
    fun `getAllCharacters returns success result when API call is successful`() = runTest {
        // Mock API response
        val mockCharacters = (0..5).map (UserTestData::getUser)
        coEvery { characterService.getUser() } returns UserResponse(success = true, mockCharacters.first(), "Success!")

        // Call the function
        val result = characterRemoteDataSource.getUser()

        // Verify the result
        assertTrue(result.isSuccess)
        assertEquals(mockCharacters, result.getOrNull())
        coVerify(exactly = 1) { characterService.getUser() }
    }

    @Test
    fun `getAllCharacters returns failure result when API call throws exception`() = runTest {
        // Mock API exception
        val exception = Exception("Network error")
        coEvery { characterService.getUser() } throws exception

        // Call the function
        val result = characterRemoteDataSource.getUser()

        // Verify the result
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { characterService.getUser() }
    }
}