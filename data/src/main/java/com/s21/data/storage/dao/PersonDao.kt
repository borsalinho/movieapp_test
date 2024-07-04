package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.s21.data.storage.models.PersonEntity


@Dao
interface PersonDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(people: PersonEntity)

    @Query("SELECT * FROM persons WHERE id = :peopleId")
    suspend fun getPersonById(peopleId: Int): PersonEntity?
}