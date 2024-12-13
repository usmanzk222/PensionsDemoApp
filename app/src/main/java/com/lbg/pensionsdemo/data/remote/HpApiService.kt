package com.lbg.pensionsdemo.data.remote

import com.lbg.pensionsdemo.data.remote.model.UserResponse
import retrofit2.http.GET

interface HpApiService {
    @GET("user/random")
    suspend fun getUser(): UserResponse
}