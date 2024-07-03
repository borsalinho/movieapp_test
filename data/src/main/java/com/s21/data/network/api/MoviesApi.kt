package com.s21.data.network.api

import com.s21.data.network.model.MovieDto
import retrofit2.http.GET

interface MoviesApi {

    @GET("api/films/?format=json")
    suspend fun getAllMovieDto() : List<MovieDto>
}