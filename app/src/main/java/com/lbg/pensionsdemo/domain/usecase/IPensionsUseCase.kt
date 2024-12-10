package com.lbg.pensionsdemo.domain.usecase

import com.lbg.pensionsdemo.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface IPensionsUseCase {
    operator fun invoke(): Flow<Result<List<CharacterDomain>>>
}