package com.s21.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class PeopleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: String,
    val species: String,
    val vehicles: String,
    val starships: String,
    val created: String,
    val edited: String,
    val url: String
)
