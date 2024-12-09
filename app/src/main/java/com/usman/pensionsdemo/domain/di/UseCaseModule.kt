package com.usman.pensionsdemo.domain.di

import com.usman.pensionsdemo.domain.usecase.IPensionsUseCase
import com.usman.pensionsdemo.domain.usecase.PensionsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetAllCharactersUseCase(useCase: PensionsUseCase): IPensionsUseCase
}