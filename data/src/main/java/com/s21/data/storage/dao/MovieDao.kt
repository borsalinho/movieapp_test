package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.s21.data.storage.model.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovieEntity() : List<MovieEntity>
}