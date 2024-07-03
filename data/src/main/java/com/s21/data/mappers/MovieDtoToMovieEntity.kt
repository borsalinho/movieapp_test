package com.s21.data.mappers

import com.s21.data.network.models.MovieDto
import com.s21.data.storage.models.MovieEntity

fun MovieDto.toMovieEntity() : MovieEntity {
    return MovieEntity(
        title = title,
        episode_id = episode_id,
        opening_crawl = opening_crawl,
        director = director,
        producer = producer,
        release_date = release_date,
        characters = characters.joinToString(","),
        planets = planets.joinToString(","),
        starships = starships.joinToString(","),
        vehicles = vehicles.joinToString(","),
        species = species.joinToString(","),
        created = created,
        edited = edited,
        url = url
    )
}