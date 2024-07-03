package com.s21.domain.repository

import com.s21.domain.model.Characters
import com.s21.domain.model.People

interface PeopleRepository {
    suspend fun getPeopleByFilm(characters : Characters) : List<People>
}