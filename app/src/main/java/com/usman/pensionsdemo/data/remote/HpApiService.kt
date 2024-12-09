package com.usman.pensionsdemo.data.remote

import com.usman.pensionsdemo.data.remote.model.Character
import retrofit2.http.GET

interface HpApiService {
    @GET("api/characters")
    suspend fun getPensions(): List<Character>
}