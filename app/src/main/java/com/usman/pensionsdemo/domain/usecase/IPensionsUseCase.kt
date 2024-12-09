package com.usman.pensionsdemo.domain.usecase

import com.usman.pensionsdemo.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface IPensionsUseCase {
    operator fun invoke(): Flow<Result<List<CharacterDomain>>>
}