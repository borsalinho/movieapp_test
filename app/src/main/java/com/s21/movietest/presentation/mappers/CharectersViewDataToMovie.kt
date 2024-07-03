package com.s21.movietest.presentation.mappers

import com.s21.domain.model.Characters
import com.s21.movietest.presentation.models.CharactersViewData

fun CharactersViewData.toCharacters() : Characters {
    return Characters(
        characters = characters
    )
}