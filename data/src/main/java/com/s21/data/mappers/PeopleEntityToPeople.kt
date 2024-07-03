package com.s21.data.mappers

import com.s21.data.storage.models.PeopleEntity
import com.s21.domain.model.People

fun PeopleEntity.toPeople() : People {
    return People(
        id = this.id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hair_color = this.hair_color,
        skin_color = this.skin_color,
        eye_color = this.eye_color,
        birth_year = this.birth_year,
        gender = this.gender,
        homeworld = this.homeworld,
        films = this.films.split(","),
        species = this.species.split(","),
        vehicles = this.vehicles.split(","),
        starships = this.starships.split(","),
        created = this.created,
        edited = this.edited,
        url = this.url
    )
}