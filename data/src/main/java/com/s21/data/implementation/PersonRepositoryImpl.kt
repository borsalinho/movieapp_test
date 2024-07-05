package com.s21.data.implementation

import com.s21.data.mappers.toPeople
import com.s21.data.mappers.toPeopleEntity
import com.s21.data.network.api.StarWarsApi
import com.s21.data.network.models.PersonDto
import com.s21.data.storage.dao.PersonDao
import com.s21.data.storage.models.PersonEntity
import com.s21.domain.model.Characters
import com.s21.domain.model.Person
import com.s21.domain.repository.PersonRepository

class PersonRepositoryImpl(
    val personDao: PersonDao,
    val starWarsApi: StarWarsApi
) : PersonRepository {

    override suspend fun getPersonByFilm(characters : Characters): List<Person> {

        val peopleList = mutableListOf<PersonEntity>()

        for (url in characters.characters){
            val peopleId = url.removePrefix("https://swapi.dev/api/people/")
                        .removeSuffix("/")
                        .toInt()

            var peopleFromDB =  getPeopleFromDBById(peopleId)

            if (peopleFromDB != null) {
                peopleList.add(peopleFromDB)
                continue
            }

            var peopleEntity = getPersonFromApi(peopleId).toPeopleEntity()
            peopleEntity.id = peopleId
            insertPerson(peopleEntity)
            getPeopleFromDBById(peopleId)?.let {people ->
                peopleList.add(people)
            }

        }

        return peopleList.toList().map { it.toPeople() }
    }


    private suspend fun getPersonFromApi(peopleId : Int) : PersonDto{
        return starWarsApi.getPeopleById(peopleId = peopleId)
    }

    private suspend fun insertPerson(people : PersonEntity){
        personDao.insertPerson(people = people)
    }

    private suspend fun getPeopleFromDBById(peopleId : Int) : PersonEntity?{
        return personDao.getPersonById(peopleId = peopleId)
    }
}

