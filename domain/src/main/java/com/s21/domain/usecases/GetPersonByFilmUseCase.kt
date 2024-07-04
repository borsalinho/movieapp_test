package com.s21.domain.usecases

import com.s21.domain.model.Characters
import com.s21.domain.model.Person
import com.s21.domain.repository.PersonRepository

class GetPersonByFilmUseCase(val personRepository: PersonRepository) {
    suspend fun execute(characters : Characters) : List<Person>{
        return personRepository.getPersonByFilm(characters = characters)
    }
}