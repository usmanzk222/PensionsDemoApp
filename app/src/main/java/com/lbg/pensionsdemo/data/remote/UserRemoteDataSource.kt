package com.lbg.pensionsdemo.data.remote

import com.lbg.pensionsdemo.data.remote.model.UserResponse
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: HpApiService) :
    IUserRemoteDataSource {

    override suspend fun getUser(): Result<UserResponse> {
        return runCatching {
           service.getUser()
        }
    }
}