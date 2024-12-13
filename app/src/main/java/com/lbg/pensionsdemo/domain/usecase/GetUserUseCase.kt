package com.lbg.pensionsdemo.domain.usecase

import com.lbg.pensionsdemo.data.repository.IUsersRepository
import com.lbg.pensionsdemo.domain.model.UserDomain
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val characterRepository: IUsersRepository
) : IGetUserUseCase {

    override suspend operator fun invoke(): UserDomain? =
        characterRepository.getUser()
}