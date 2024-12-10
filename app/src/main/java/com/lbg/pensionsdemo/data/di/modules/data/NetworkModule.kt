package com.lbg.pensionsdemo.data.di.modules.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lbg.pensionsdemo.data.remote.HpApiService
import com.lbg.pensionsdemo.data.remote.adapters.HouseTypeAdapter
import com.lbg.pensionsdemo.data.di.BaseUrl
import com.lbg.pensionsdemo.domain.model.House
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://hp-api.onrender.com/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(House::class.java, HouseTypeAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build()) // Add OkHttpClient if needed
            .build()
    }

    @Provides
    @Singleton
    fun provideHarryPotterService(retrofit: Retrofit): HpApiService {
        return retrofit.create(HpApiService::class.java)
    }
}