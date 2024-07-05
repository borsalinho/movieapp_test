package com.s21.movietest.presentation.ui.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.GetAllMoviesUseCase
import com.s21.domain.usecases.GetPersonByFilmUseCase
import com.s21.domain.usecases.GetPlanetByFilmUseCase
import com.s21.movietest.presentation.mappers.toCharacters
import com.s21.movietest.presentation.mappers.toHomeWorld
import com.s21.movietest.presentation.mappers.toMovieViewModel
import com.s21.movietest.presentation.mappers.toPersonViewData
import com.s21.movietest.presentation.mappers.toPlanetViewData
import com.s21.movietest.presentation.models.CharactersViewData
import com.s21.movietest.presentation.models.MovieNameViewData
import com.s21.movietest.presentation.models.MovieViewData
import com.s21.movietest.presentation.models.PersonViewData
import com.s21.movietest.presentation.models.HomeWorldViewData
import com.s21.movietest.presentation.models.PersonNameViewData
import com.s21.movietest.presentation.models.PlanetViewData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivityViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getPersonByFilmUseCase: GetPersonByFilmUseCase,
    private val getPlanetByFilmUseCase : GetPlanetByFilmUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieViewData>>()
    val movies: LiveData<List<MovieViewData>> = _movies

    private val _persons = MutableLiveData<List<PersonViewData>>()
    val persons: LiveData<List<PersonViewData>> = _persons

    private val _planet = MutableLiveData<PlanetViewData>()
    val planet : LiveData<PlanetViewData> = _planet

    private val _personsList = MutableLiveData<CharactersViewData>()
    val personsList : LiveData<CharactersViewData> = _personsList

    private val _homeWorld = MutableLiveData<HomeWorldViewData>()
    val homeWorld : LiveData<HomeWorldViewData> = _homeWorld

    private val _movieName = MutableLiveData<MovieNameViewData>()
    val movieName : LiveData<MovieNameViewData> = _movieName

    private val _personName = MutableLiveData<PersonNameViewData>()
    val personName : LiveData<PersonNameViewData> = _personName


    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error




    fun getPersons(){
        viewModelScope.launch {
            try {
                val chars: CharactersViewData? = personsList.value
                if (chars == null) {
                    throw IllegalArgumentException("Не удалось получить список персонажей")
                }
                _persons.value = getPersonByFilmUseCase
                    .execute(chars.toCharacters()).map { it.toPersonViewData() }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun getAllMovies()  {
        viewModelScope.launch {
            try {
                _movies.value = getAllMoviesUseCase.execute().map { it.toMovieViewModel() }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun getPlanet(){
        viewModelScope.launch {
            try {
                val homeWorld: HomeWorldViewData? = homeWorld.value
                if (homeWorld == null) {
                    throw IllegalArgumentException("Не удалось получить планету")
                }
                _planet.value = getPlanetByFilmUseCase
                    .execute(homeWorld.toHomeWorld()).toPlanetViewData()
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    fun setPersonsList(charactersViewData : CharactersViewData){
        _personsList.value = charactersViewData
    }
    fun setHomeWorld(homeWorldViewData : HomeWorldViewData){
        _homeWorld.value = homeWorldViewData
    }
    fun setMovieName(movieNameViewData : MovieNameViewData){
        _movieName.value = movieNameViewData
    }
    fun setPersonName(personNameViewData : PersonNameViewData){
        _personName.value = personNameViewData
    }


    fun onErrorShown() {
        _error.value = null
    }

    private fun handleError(e: Exception) {
        when (e) {
            is HttpException -> _error.value = "Ошибка сети: ${e.message()}"
            is IOException -> _error.value = "Ошибка ввода-вывода: ${e.message}"
            else -> _error.value = "Не удалось загрузить данные: ${e.message}"
        }
    }

}