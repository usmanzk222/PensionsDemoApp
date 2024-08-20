package com.usman.harrypotter.domain.di

import com.usman.harrypotter.domain.usecase.IGetAllCharactersUseCase
import com.usman.harrypotter.domain.usecase.GetAllCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetAllCharactersUseCase(useCase: GetAllCharactersUseCase): IGetAllCharactersUseCase
}