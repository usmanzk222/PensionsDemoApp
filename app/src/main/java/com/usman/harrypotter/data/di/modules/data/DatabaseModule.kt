package com.usman.harrypotter.data.di.modules.data

import android.app.Application
import androidx.room.Room
import com.usman.harrypotter.data.local.database.CharacterDao
import com.usman.harrypotter.data.local.database.CharacterDatabase
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
    fun provideCharacterDatabase(application: Application): CharacterDatabase {
        return Room.databaseBuilder(
            application,
            CharacterDatabase::class.java,
            "character_database"
        ).build()
    }

    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }
}