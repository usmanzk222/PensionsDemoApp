package com.lbg.pensionsdemo.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lbg.pensionsdemo.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PensionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<UserEntity>)

    @Update
    suspend fun update(character: UserEntity)

    @Delete
    suspend fun delete(character: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun getCharacterById(id: Int): UserEntity?

    @Query("SELECT * FROM UserEntity")
    fun getAllCharacters(): Flow<List<UserEntity>>
}