package com.usman.pensionsdemo.data.remote

import com.usman.pensionsdemo.data.remote.model.Character

interface IPensionsRemoteDataSource {
    suspend fun getPensions(): Result<List<Character>>
}