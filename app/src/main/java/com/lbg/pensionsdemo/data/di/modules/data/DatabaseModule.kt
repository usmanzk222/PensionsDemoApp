package com.lbg.pensionsdemo.data.di.modules.data

import android.app.Application
import androidx.room.Room
import com.lbg.pensionsdemo.data.local.database.PensionsDao
import com.lbg.pensionsdemo.data.local.database.PensionsDemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(application: Application): PensionsDemoDatabase {
        return Room.databaseBuilder(
            application,
            PensionsDemoDatabase::class.java,
            "pensions_demo_database"
        ).build()
    }

    @Provides
    fun provideCharacterDao(database: PensionsDemoDatabase): PensionsDao {
        return database.characterDao()
    }
}