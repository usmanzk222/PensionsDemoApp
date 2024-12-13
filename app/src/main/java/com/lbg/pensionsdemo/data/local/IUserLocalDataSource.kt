package com.lbg.pensionsdemo.data.local

import com.lbg.pensionsdemo.data.local.entity.UserEntity

interface IUserLocalDataSource {
    suspend fun getUser(): UserEntity?
    suspend fun insertUser(characters: UserEntity)
}