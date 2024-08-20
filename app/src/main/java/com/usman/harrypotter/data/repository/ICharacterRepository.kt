package com.usman.harrypotter.data.repository

import com.usman.harrypotter.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    fun getAllCharacters(): Flow<Result<List<CharacterDomain>>>

    suspend fun getCharacterById(id: String): CharacterDomain?
}