package com.s21.domain.repository

import com.s21.domain.model.Characters
import com.s21.domain.model.Person

interface PersonRepository {
    suspend fun getPersonByFilm(characters : Characters) : List<Person>
}