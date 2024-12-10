package com.lbg.pensionsdemo.data.remote

import com.lbg.pensionsdemo.data.remote.model.Character
import retrofit2.http.GET

interface HpApiService {
    @GET("api/characters")
    suspend fun getPensions(): List<Character>
}