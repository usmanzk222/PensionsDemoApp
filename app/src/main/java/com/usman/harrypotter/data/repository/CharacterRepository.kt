package com.usman.harrypotter.data.repository

import android.util.Log
import com.usman.harrypotter.data.local.ICharacterLocalDataSource
import com.usman.harrypotter.data.local.entity.toCharacterDomain
import com.usman.harrypotter.data.remote.ICharacterRemoteDataSource
import com.usman.harrypotter.data.remote.model.toCharacterEntity
import com.usman.harrypotter.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterLocalDataSource: ICharacterLocalDataSource,
    private val characterRemoteDataSource: ICharacterRemoteDataSource
) : ICharacterRepository {

    override fun getAllCharacters(): Flow<Result<List<CharacterDomain>>> = flow {
        // Fetch remote data and update local cache
        val remoteResult = characterRemoteDataSource.getAllCharacters()
            .onSuccess { remoteCharacters ->
                characterLocalDataSource.insertAllCharacters(remoteCharacters.map { it.toCharacterEntity() })
            }
            .onFailure { exception ->
                Log.e(CharacterRepository::class.java.name, exception.message?:"Something went wrong")
            }

        // Emit local data
        val roomDataFlow: Flow<Result<List<CharacterDomain>>> = characterLocalDataSource
            .getAllCharacters()
            .map {
                if (it.isNotEmpty()) {
                    Result.success(it.map { characterEntity -> characterEntity.toCharacterDomain() })
                } else {
                    Result.failure(remoteResult.exceptionOrNull() ?: Throwable("No data found"))
                }
            }
        emitAll(roomDataFlow)
    }

    override suspend fun getCharacterById(id: String): CharacterDomain? {
        return characterLocalDataSource.getCharacterById(id)?.toCharacterDomain()
    }
}