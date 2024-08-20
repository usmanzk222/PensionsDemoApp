package com.usman.harrypotter.data.local

import com.usman.harrypotter.data.local.database.CharacterDao
import com.usman.harrypotter.data.local.entity.CharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(private val characterDao: CharacterDao) :
    ICharacterLocalDataSource {

    override fun getAllCharacters():
            Flow<List<CharacterEntity>> = characterDao
        .getAllCharacters().flowOn(Dispatchers.IO)

    override suspend fun getCharacterById(id: String):
            CharacterEntity? {
        return withContext(Dispatchers.IO){
            characterDao.getCharacterById(id)
        }
    }

    override suspend fun insertAllCharacters(characters: List<CharacterEntity>) {
        withContext(Dispatchers.IO){
            runCatching {
                characterDao.insertAll(characters)
            }

        }
    }
}