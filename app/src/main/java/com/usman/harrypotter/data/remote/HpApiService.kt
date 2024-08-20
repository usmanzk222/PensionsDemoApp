package com.usman.harrypotter.data.remote

import com.usman.harrypotter.data.remote.model.Character
import retrofit2.http.GET

interface HpApiService {
    @GET("api/characters")
    suspend fun getCharacters(): List<Character>
}