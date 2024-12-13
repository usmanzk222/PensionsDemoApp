package com.lbg.pensionsdemo.data.remote

import com.lbg.pensionsdemo.data.remote.model.UserResponse

interface IUserRemoteDataSource {
    suspend fun getUser(): Result<UserResponse>
}