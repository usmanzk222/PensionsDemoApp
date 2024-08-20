package com.usman.harrypotter.domain.usecase

import com.usman.harrypotter.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface IGetAllCharactersUseCase {
    operator fun invoke(): Flow<Result<List<CharacterDomain>>>
}