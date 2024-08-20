package com.usman.harrypotter.data.remote

import com.usman.harrypotter.data.remote.model.Character

interface ICharacterRemoteDataSource {
    suspend fun getAllCharacters(): Result<List<Character>>
}