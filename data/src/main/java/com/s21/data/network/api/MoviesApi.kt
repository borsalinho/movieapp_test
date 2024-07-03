package com.s21.data.network.api

import com.s21.data.network.model.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("api/films/?format=json")
    suspend fun getAllMovieDto() : MovieListDto
}