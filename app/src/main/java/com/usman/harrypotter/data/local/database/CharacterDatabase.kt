package com.usman.harrypotter.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.usman.harrypotter.data.local.entity.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(HouseConverter::class) // Add TypeConverter for House enum
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}