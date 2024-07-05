package com.s21.data.network.api

import com.s21.data.network.models.MovieListDto
import com.s21.data.network.models.PersonDto
import com.s21.data.network.models.PlanetDto
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {

    @GET("api/films/?format=json")
    suspend fun getAllMovieDto() : MovieListDto

    @GET("api/people/{id}")
    suspend fun getPeopleById(@Path("id") peopleId : Int) : PersonDto

    @GET("api/planets/{id}")
    suspend fun getPlanetById(@Path("id") planetId : Int) : PlanetDto
}