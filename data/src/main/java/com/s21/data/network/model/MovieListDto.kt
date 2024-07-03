package com.s21.data.network.model

data class MovieListDto(
    val count : Int,
    val next : String? = null,
    val previous :  String? = null,
    val results : List<MovieDto>

)
