package com.s21.data.implementation

import android.util.Log
import com.s21.data.mappers.toMovieEntity
import com.s21.data.network.api.MoviesApi
import com.s21.data.network.model.MovieListDto
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.model.MovieEntity
import com.s21.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val movieDao: MovieDao,
    val moviesApi: MoviesApi
) : MovieRepository {
    override suspend fun loadAllMovies() {
        val resultFromApi =  loadAllMoviesFromApi()
        saveAllMoviesToDataBase(resultFromApi.results.map { it.toMovieEntity() })
    }

    private suspend fun loadAllMoviesFromApi() : MovieListDto {
        return moviesApi.getAllMovieDto()
    }

    private suspend fun saveAllMoviesToDataBase(allMovies : List<MovieEntity>) {
        movieDao.insert(allMovies)
    }
}