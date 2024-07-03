package com.s21.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.dao.PeopleDao
import com.s21.data.storage.models.MovieEntity
import com.s21.data.storage.models.PeopleEntity


@Database(
    entities = [
        MovieEntity::class,
        PeopleEntity::class
    ],
    version = 1
)
abstract class MyAppDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao
    abstract fun peopleDao() : PeopleDao
}