package com.s21.data.implementation

import com.s21.data.mappers.toMovie
import com.s21.data.mappers.toPlanet
import com.s21.data.mappers.toPlanetEntity
import com.s21.data.network.api.StarWarsApi
import com.s21.data.network.models.PlanetDto
import com.s21.data.storage.dao.PlanetDao
import com.s21.data.storage.models.PlanetEntity
import com.s21.domain.model.HomeWorld
import com.s21.domain.model.Planet
import com.s21.domain.repository.PlanetRepository

class PlanetRepositoryImpl(
    val planetDao: PlanetDao,
    val starWarsApi: StarWarsApi
) : PlanetRepository {
    override suspend fun getPlanetByPerson(homeWorld: HomeWorld): Planet {
        val planetId = homeWorld.url.removePrefix("https://swapi.dev/api/planet/")
            .removeSuffix("/")
            .toInt()
        val planetEntityFromBD = getPlanetFromDBById(planetId)

        if (planetEntityFromBD != null){
            planetEntityFromBD.id = planetId
            return planetEntityFromBD.toPlanet()
        }
        var planetDtoFromApi = getPlanetFromApi(planetId)
        insertPlanet(planetDtoFromApi.toPlanetEntity())
        return  getPlanetFromDBById(planetId)?.toPlanet() ?: throw RuntimeException("Не удалось выгрузить из БД")
    }

    private suspend fun getPlanetFromApi(planetId : Int) : PlanetDto {
        return starWarsApi.getPlanetById(planetId = planetId)
    }

    private suspend fun insertPlanet(planet : PlanetEntity){
        planetDao.insertPlanet(planet = planet)
    }

    private suspend fun getPlanetFromDBById(planetId : Int) : PlanetEntity?{
        return planetDao.getPlanetById(planetId = planetId)
    }
}