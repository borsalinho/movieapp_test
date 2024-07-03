package com.s21.data.network.api

import com.s21.data.network.models.MovieListDto
import com.s21.data.network.models.PeopleDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {

    @GET("api/films/?format=json")
    suspend fun getAllMovieDto() : MovieListDto

    @GET("api/people/{id}")
    suspend fun getPeopleById(@Path("id") peopleId : Int) : PeopleDto
}