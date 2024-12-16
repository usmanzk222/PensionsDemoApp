package com.lbg.pensionsdemo.data.local

import com.lbg.pensionsdemo.data.local.database.PensionsDao
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val characterDao: PensionsDao) :
    IUserLocalDataSource {

    override suspend fun getUser(): UserEntity? = characterDao.getFirstUser()


    override suspend fun insertUser(characters: UserEntity) {
        withContext(Dispatchers.IO){
            runCatching {
                characterDao.insert(characters)
            }

        }
    }
}