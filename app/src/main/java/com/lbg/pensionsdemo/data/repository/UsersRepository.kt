package com.lbg.pensionsdemo.data.repository

import android.util.Log
import com.lbg.pensionsdemo.data.local.IUserLocalDataSource
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import com.lbg.pensionsdemo.data.local.entity.toUserDomain
import com.lbg.pensionsdemo.data.remote.IUserRemoteDataSource
import com.lbg.pensionsdemo.data.remote.model.toUserEntity
import com.lbg.pensionsdemo.domain.model.UserDomain
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userLocalDataSource: IUserLocalDataSource,
    private val userRemoteDataSource: IUserRemoteDataSource
) : IUsersRepository {

    override suspend fun getUser(): UserDomain? {
         try {
            val remoteResult = userRemoteDataSource.getUser()
            if(remoteResult.isSuccess){
                val remoteUser = remoteResult.getOrNull()?.data?.toUserEntity() // Map to UserEntity
                remoteUser?.let {
                    userLocalDataSource.insertUser(it)
                    return it.toUserDomain()
                }
            }

            Log.e("UsersRepository", "Api Call to fetch User Failed ${remoteResult.exceptionOrNull()?.message}")
            return getLocalUser()
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun getLocalUser(): UserDomain{
        val localUser = userLocalDataSource.getUser() ?: UserEntity(
            id = 1, name = "Sarah", email = "sarah@gmail.com", age = 32, ordinalAge = "32nd",
            dob = 703440000000,
            pensions = 1,
        )
        return localUser.toUserDomain() ?: run {
            throw Exception("User not found")
        }
    }
}