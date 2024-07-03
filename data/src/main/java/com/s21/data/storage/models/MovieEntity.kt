package com.s21.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val episode_id: Int,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val characters: String,
    val planets: String,
    val starships: String,
    val vehicles: String,
    val species: String,
    val created: String,
    val edited: String,
    val url: String
)
