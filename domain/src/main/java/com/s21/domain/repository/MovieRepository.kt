package com.s21.domain.repository

import com.s21.domain.model.Movie

interface MovieRepository {
    suspend fun loadAllMovies() : List<Movie>
}