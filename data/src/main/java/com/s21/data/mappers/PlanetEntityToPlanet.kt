package com.s21.data.mappers

import com.s21.data.storage.models.PlanetEntity
import com.s21.domain.model.Planet

fun PlanetEntity.toPlanet() : Planet {
    return Planet(
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
        residents =  residents.split(","),
        films = films.split(","),
        created = created,
        edited = edited,
        url = url
    )
}

