package com.lbg.pensionsdemo.domain.usecase

import com.lbg.pensionsdemo.domain.model.UserDomain

interface IGetUserUseCase {
    suspend operator fun invoke(): UserDomain?
}