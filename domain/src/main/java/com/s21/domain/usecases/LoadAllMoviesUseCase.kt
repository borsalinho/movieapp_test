package com.s21.domain.usecases

import com.s21.domain.model.Movie
import com.s21.domain.repository.MovieRepository

class LoadAllMoviesUseCase(val movieRepository: MovieRepository) {
    suspend fun execute() : List<Movie>{
        return movieRepository.loadAllMovies()
    }
}