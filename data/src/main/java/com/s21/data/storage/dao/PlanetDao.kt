package com.s21.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.s21.data.storage.models.PlanetEntity

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity)

    @Query("SELECT * FROM planets WHERE id = :planetId")
    suspend fun getPlanetById(planetId: Int): PlanetEntity?
}