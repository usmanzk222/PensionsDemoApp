package com.usman.pensionsdemo.data.di.modules.data

import com.usman.pensionsdemo.data.local.IPensionsLocalDataSource
import com.usman.pensionsdemo.data.local.PensionsLocalDataSource
import com.usman.pensionsdemo.data.remote.IPensionsRemoteDataSource
import com.usman.pensionsdemo.data.remote.PensionsRemoteDataSource
import com.usman.pensionsdemo.data.repository.PensionsRepository
import com.usman.pensionsdemo.data.repository.IPensionsRepository
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
    abstract fun provideCharacterLocalDataSource(dataSource: PensionsLocalDataSource): IPensionsLocalDataSource


    @Binds
    @Singleton
    abstract fun provideCharacterRemoteDataSource(dataSource: PensionsRemoteDataSource): IPensionsRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideCharacterRepository(dataSource: PensionsRepository): IPensionsRepository



}