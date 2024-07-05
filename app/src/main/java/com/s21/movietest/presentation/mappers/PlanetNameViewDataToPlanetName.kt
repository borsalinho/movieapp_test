package com.s21.movietest.presentation.mappers

import com.s21.domain.model.HomeWorld
import com.s21.movietest.presentation.models.HomeWorldViewData

fun HomeWorldViewData.toHomeWorld() : HomeWorld {
    return HomeWorld(
        url = url
    )
}