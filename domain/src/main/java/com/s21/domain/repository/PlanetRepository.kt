package com.s21.domain.repository

import com.s21.domain.model.HomeWorld
import com.s21.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanetByPerson(homeWorld : HomeWorld) : Planet
}