package com.lbg.pensionsdemo.data.remote

import com.lbg.pensionsdemo.data.remote.model.Character

interface IPensionsRemoteDataSource {
    suspend fun getPensions(): Result<List<Character>>
}