package com.s21.movietest.presentation.mappers

import com.s21.domain.model.Movie
import com.s21.movietest.presentation.models.MovieViewData

fun Movie.toMovieViewModel() : MovieViewData{
    return MovieViewData(
        id = id,
        title = title,
        episode_id = episode_id,
        opening_crawl = opening_crawl,
        director = director,
        producer = producer,
        release_date = release_date,
        characters = characters,
        planets = planets,
        starships = starships,
        vehicles = vehicles,
        species = species,
        created = created,
        edited = edited,
        url = url
    )
}