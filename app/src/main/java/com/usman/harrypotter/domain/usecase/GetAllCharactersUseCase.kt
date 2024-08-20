package com.usman.harrypotter.domain.usecase

import com.usman.harrypotter.data.repository.ICharacterRepository
import com.usman.harrypotter.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) : IGetAllCharactersUseCase {

    override operator fun invoke(): Flow<Result<List<CharacterDomain>>> =
        characterRepository.getAllCharacters()
}