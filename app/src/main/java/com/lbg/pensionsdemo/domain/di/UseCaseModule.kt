package com.lbg.pensionsdemo.domain.di

import com.lbg.pensionsdemo.domain.usecase.GetUserUseCase
import com.lbg.pensionsdemo.domain.usecase.IGetUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetAllCharactersUseCase(useCase: GetUserUseCase): IGetUserUseCase
}