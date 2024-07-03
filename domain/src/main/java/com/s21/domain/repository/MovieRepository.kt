package com.s21.domain.repository

interface MovieRepository {
    suspend fun loadAllMovies()
}