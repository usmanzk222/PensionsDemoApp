package com.lbg.pensionsdemo.data.repository

import android.util.Log
import com.lbg.pensionsdemo.data.local.IPensionsLocalDataSource
import com.lbg.pensionsdemo.data.local.entity.toCharacterDomain
import com.lbg.pensionsdemo.data.remote.IPensionsRemoteDataSource
import com.lbg.pensionsdemo.data.remote.model.toCharacterEntity
import com.lbg.pensionsdemo.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PensionsRepository @Inject constructor(
    private val characterLocalDataSource: IPensionsLocalDataSource,
    private val characterRemoteDataSource: IPensionsRemoteDataSource
) : IPensionsRepository {

    override fun getPensions(): Flow<Result<List<CharacterDomain>>> = flow {
        // Fetch remote data and update local cache
        val remoteResult = characterRemoteDataSource.getPensions()
            .onSuccess { remoteCharacters ->
                characterLocalDataSource.insertAllCharacters(remoteCharacters.map { it.toCharacterEntity() })
            }
            .onFailure { exception ->
                Log.e(PensionsRepository::class.java.name, exception.message?:"Something went wrong")
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

    override suspend fun getPensionsById(id: String): CharacterDomain? {
        return characterLocalDataSource.getCharacterById(id)?.toCharacterDomain()
    }
}