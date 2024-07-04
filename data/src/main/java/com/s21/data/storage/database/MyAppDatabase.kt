package com.s21.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.dao.PersonDao
import com.s21.data.storage.models.MovieEntity
import com.s21.data.storage.models.PersonEntity


@Database(
    entities = [
        MovieEntity::class,
        PersonEntity::class
    ],
    version = 1
)
abstract class MyAppDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao
    abstract fun personDao() : PersonDao
}