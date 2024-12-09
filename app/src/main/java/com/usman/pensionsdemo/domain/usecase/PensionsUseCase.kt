package com.usman.pensionsdemo.domain.usecase

import com.usman.pensionsdemo.data.repository.IPensionsRepository
import com.usman.pensionsdemo.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PensionsUseCase @Inject constructor(
    private val characterRepository: IPensionsRepository
) : IPensionsUseCase {

    override operator fun invoke(): Flow<Result<List<CharacterDomain>>> =
        characterRepository.getPensions()
}