package com.s21.domain.usecases

import com.s21.domain.model.HomeWorld
import com.s21.domain.model.Planet
import com.s21.domain.repository.PlanetRepository

class GetPlanetByFilmUseCase(val planetRepository: PlanetRepository) {
    suspend fun execute(homeWorld: HomeWorld) : Planet{
        return planetRepository.getPlanetByPerson(homeWorld = homeWorld)
    }
}