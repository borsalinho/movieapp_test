package com.s21.data.mappers

import com.s21.data.network.models.PlanetDto
import com.s21.data.storage.models.PlanetEntity

fun PlanetDto.toPlanetEntity() : PlanetEntity {
    return PlanetEntity(
        id = 0, // все равно потом пприсвоим вручную
        name = name,
        rotation_period = rotation_period,
        orbital_period = orbital_period,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surface_water = surface_water,
        population = population,
        residents =  residents.joinToString(","),
        films = films.joinToString(","),
        created = created,
        edited = edited,
        url = url
    )
}