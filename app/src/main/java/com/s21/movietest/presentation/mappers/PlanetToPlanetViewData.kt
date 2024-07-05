package com.s21.movietest.presentation.mappers

import com.s21.domain.model.Planet
import com.s21.movietest.presentation.models.PlanetViewData

fun Planet.toPlanetViewData() : PlanetViewData {
    return PlanetViewData(
        id = id,
        name = name,
        rotation_period = rotation_period,
        orbital_period = orbital_period,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surface_water = surface_water,
        population = population,
        residents =  residents,
        films = films,
        created = created,
        edited = edited,
        url = url
    )
}