package com.lbg.pensionsdemo.data.repository

import com.lbg.pensionsdemo.domain.model.UserDomain

interface IUsersRepository {
    suspend fun getUser(): UserDomain?
}