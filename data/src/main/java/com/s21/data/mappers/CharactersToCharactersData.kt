package com.s21.data.mappers

import com.s21.data.models.CharactersData
import com.s21.domain.model.Characters

fun Characters.toCharactersData() : CharactersData {
    return CharactersData(
        characters = characters
    )
}