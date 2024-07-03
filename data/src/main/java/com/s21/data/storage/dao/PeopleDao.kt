package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.s21.data.storage.models.PeopleEntity


@Dao
interface PeopleDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: PeopleEntity)

    @Query("SELECT * FROM people WHERE id = :peopleId")
    suspend fun getPeopleById(peopleId: Int): PeopleEntity?
}