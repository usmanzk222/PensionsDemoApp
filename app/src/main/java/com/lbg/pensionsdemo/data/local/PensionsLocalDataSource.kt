package com.lbg.pensionsdemo.data.local

import com.lbg.pensionsdemo.data.local.database.PensionsDao
import com.lbg.pensionsdemo.data.local.entity.CharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PensionsLocalDataSource @Inject constructor(private val characterDao: PensionsDao) :
    IPensionsLocalDataSource {

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