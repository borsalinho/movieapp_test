package com.s21.data.mappers

import com.s21.data.network.models.PersonDto
import com.s21.data.storage.models.PersonEntity

fun PersonDto.toPeopleEntity() : PersonEntity{
    return PersonEntity(
        id = 0, // все равно потом перепишем
        name = this.name,
        height = this.height,
        mass = this.mass,
        hair_color = this.hair_color,
        skin_color = this.skin_color,
        eye_color = this.eye_color,
        birth_year = this.birth_year,
        gender = this.gender,
        homeworld = this.homeworld,
        films = this.films.joinToString(","),
        species = this.species.joinToString(","),
        vehicles = this.vehicles.joinToString(","),
        starships = this.starships.joinToString(","),
        created = this.created,
        edited = this.edited,
        url = this.url
    )
}