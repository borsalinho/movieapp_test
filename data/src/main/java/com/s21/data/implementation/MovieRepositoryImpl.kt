package com.s21.data.implementation

import com.s21.data.mappers.toMovie
import com.s21.data.mappers.toMovieEntity
import com.s21.data.network.api.MoviesApi
import com.s21.data.network.model.MovieListDto
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.model.MovieEntity
import com.s21.domain.model.Movie
import com.s21.domain.repository.MovieRepository

class MovieRepositoryImpl(
    val movieDao: MovieDao,
    val moviesApi: MoviesApi
) : MovieRepository {
    override suspend fun loadAllMovies() : List<Movie> {
        val allMoviesFromDataBase = movieDao.getAllMovieEntity()
        if (allMoviesFromDataBase.isNotEmpty()){
            return allMoviesFromDataBase.map { it.toMovie() }
        }
        val resultFromApi = loadAllMoviesFromApi()
        saveAllMoviesToDataBase(resultFromApi.results.map { it.toMovieEntity() })
        return movieDao.getAllMovieEntity().map { it.toMovie() }
    }

    private suspend fun loadAllMoviesFromApi() : MovieListDto {
        return moviesApi.getAllMovieDto()
    }

    private suspend fun saveAllMoviesToDataBase(allMovies : List<MovieEntity>) {
        movieDao.insert(allMovies)
    }
}