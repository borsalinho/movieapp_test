package com.s21.domain.usecases

import com.s21.domain.model.Characters
import com.s21.domain.model.People
import com.s21.domain.repository.PeopleRepository

class GetPeoplesByFilmUseCase(val peopleRepository: PeopleRepository) {
    suspend fun execute(characters : Characters) : List<People>{
        return peopleRepository.getPeopleByFilm(characters = characters)
    }
}