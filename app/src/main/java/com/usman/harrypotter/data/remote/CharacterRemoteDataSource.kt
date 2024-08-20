package com.usman.harrypotter.data.remote

import javax.inject.Inject
import com.usman.harrypotter.data.remote.model.Character

class CharacterRemoteDataSource @Inject constructor(private val characterService: HpApiService) :
    ICharacterRemoteDataSource {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return runCatching {
           characterService.getCharacters()
        }
    }
}