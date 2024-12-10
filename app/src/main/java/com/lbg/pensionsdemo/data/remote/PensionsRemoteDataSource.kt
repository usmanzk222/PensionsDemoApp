package com.lbg.pensionsdemo.data.remote

import javax.inject.Inject
import com.lbg.pensionsdemo.data.remote.model.Character

class PensionsRemoteDataSource @Inject constructor(private val service: HpApiService) :
    IPensionsRemoteDataSource {

    override suspend fun getPensions(): Result<List<Character>> {
        return runCatching {
           service.getPensions()
        }
    }
}