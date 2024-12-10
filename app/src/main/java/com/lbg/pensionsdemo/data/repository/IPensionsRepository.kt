package com.lbg.pensionsdemo.data.repository

import com.lbg.pensionsdemo.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface IPensionsRepository {
    fun getPensions(): Flow<Result<List<CharacterDomain>>>

    suspend fun getPensionsById(id: String): CharacterDomain?
}