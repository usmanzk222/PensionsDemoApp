package com.lbg.pensionsdemo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lbg.pensionsdemo.data.local.entity.CharacterEntity


@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(HouseConverter::class) // Add TypeConverter for House enum
abstract class PensionsDemoDatabase : RoomDatabase() {
    abstract fun characterDao(): PensionsDao
}