package com.usman.harrypotter.data.di.modules.data

import com.usman.harrypotter.data.local.ICharacterLocalDataSource
import com.usman.harrypotter.data.local.CharacterLocalDataSource
import com.usman.harrypotter.data.remote.ICharacterRemoteDataSource
import com.usman.harrypotter.data.remote.CharacterRemoteDataSource
import com.usman.harrypotter.data.repository.CharacterRepository
import com.usman.harrypotter.data.repository.ICharacterRepository
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
    abstract fun provideCharacterLocalDataSource(dataSource: CharacterLocalDataSource): ICharacterLocalDataSource


    @Binds
    @Singleton
    abstract fun provideCharacterRemoteDataSource(dataSource: CharacterRemoteDataSource): ICharacterRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideCharacterRepository(dataSource: CharacterRepository): ICharacterRepository



}