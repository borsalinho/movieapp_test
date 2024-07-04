package com.s21.movietest.presentation.mappers

import com.s21.domain.model.Person
import com.s21.movietest.presentation.models.PersonViewData

fun Person.toPersonViewData() : PersonViewData {
    return PersonViewData(
        id = id,
        name = this.name,
        height = this.height,
        mass = this.mass,
        hair_color = this.hair_color,
        skin_color = this.skin_color,
        eye_color = this.eye_color,
        birth_year = this.birth_year,
        gender = this.gender,
        homeworld = this.homeworld,
        films = this.films,
        species = this.species,
        vehicles = this.vehicles,
        starships = this.starships,
        created = this.created,
        edited = this.edited,
        url = this.url
    )
}