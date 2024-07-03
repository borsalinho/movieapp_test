package com.s21.data.mappers

import com.s21.data.storage.models.MovieEntity
import com.s21.domain.model.Movie

fun MovieEntity.toMovie() : Movie {
    return Movie(
        id = id,
        title = title,
        episode_id = episode_id,
        opening_crawl = opening_crawl,
        director = director,
        producer = producer,
        release_date = release_date,
        characters = characters.split(","),
        planets = planets.split(","),
        starships = starships.split(","),
        vehicles = vehicles.split(","),
        species = species.split(","),
        created = created,
        edited = edited,
        url = url
    )
}