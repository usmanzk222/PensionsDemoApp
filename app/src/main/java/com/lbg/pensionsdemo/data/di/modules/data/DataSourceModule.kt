package com.lbg.pensionsdemo.data.di.modules.data

import com.lbg.pensionsdemo.data.local.IUserLocalDataSource
import com.lbg.pensionsdemo.data.local.UserLocalDataSource
import com.lbg.pensionsdemo.data.remote.IUserRemoteDataSource
import com.lbg.pensionsdemo.data.remote.UserRemoteDataSource
import com.lbg.pensionsdemo.data.repository.IUsersRepository
import com.lbg.pensionsdemo.data.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideCharacterLocalDataSource(dataSource: UserLocalDataSource): IUserLocalDataSource


    @Binds
    @Singleton
    abstract fun provideCharacterRemoteDataSource(dataSource: UserRemoteDataSource): IUserRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideCharacterRepository(dataSource: UsersRepository): IUsersRepository



}