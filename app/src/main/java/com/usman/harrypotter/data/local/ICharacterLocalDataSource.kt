package com.usman.harrypotter.data.local

import com.usman.harrypotter.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface ICharacterLocalDataSource {
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacterById(id: String): CharacterEntity?
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)
}