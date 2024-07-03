package com.s21.data.implementation

import com.s21.data.mappers.toCharactersData
import com.s21.data.mappers.toPeople
import com.s21.data.mappers.toPeopleEntity
import com.s21.data.models.CharactersData
import com.s21.data.network.api.StarWarsApi
import com.s21.data.network.models.PeopleDto
import com.s21.data.storage.dao.PeopleDao
import com.s21.data.storage.models.PeopleEntity
import com.s21.domain.model.Characters
import com.s21.domain.model.People
import com.s21.domain.repository.PeopleRepository

class PeopleRepositoryImpl(
    val peopleDao: PeopleDao,
    val starWarsApi: StarWarsApi
) : PeopleRepository {

    override suspend fun getPeopleByFilm(characters : Characters): List<People> {

        val charactersData : CharactersData = characters.toCharactersData()
        val peopleList = mutableListOf<PeopleEntity>()

        for (url in charactersData.characters){
            val peopleId = url.removePrefix("https://swapi.dev/api/people/")
                        .removeSuffix("/")
                        .toInt()

            var peopleFromDB =  getPeopleFromDBById(peopleId)

            if (peopleFromDB != null) {
                peopleList.add(peopleFromDB)
                continue
            }

            insertPerson(getPeopleFromApi(peopleId).toPeopleEntity())
            getPeopleFromDBById(peopleId)?.let {people ->
                peopleList.add(people)
            }

        }

        return peopleList.toList().map { it.toPeople() }
    }


    private suspend fun getPeopleFromApi(peopleId : Int) : PeopleDto{
        return starWarsApi.getPeopleById(peopleId = peopleId)
    }

    private suspend fun insertPerson(people : PeopleEntity){
        peopleDao.insertPeople(people = people)
    }

    private suspend fun getPeopleFromDBById(peopleId : Int) : PeopleEntity?{
        return peopleDao.getPeopleById(peopleId = peopleId)
    }
}

