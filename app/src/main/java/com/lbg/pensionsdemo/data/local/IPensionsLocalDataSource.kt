package com.lbg.pensionsdemo.data.local

import com.lbg.pensionsdemo.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface IPensionsLocalDataSource {
    fun getAllCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacterById(id: String): CharacterEntity?
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)
}