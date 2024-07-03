package com.s21.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.model.MovieEntity


@Database(entities = [MovieEntity::class], version = 1)
abstract class MyAppDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}