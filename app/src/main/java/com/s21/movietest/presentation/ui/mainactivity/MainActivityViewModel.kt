package com.s21.movietest.presentation.ui.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s21.domain.model.Characters
import com.s21.domain.usecases.GetAllMoviesUseCase
import com.s21.domain.usecases.GetPersonByFilmUseCase
import com.s21.movietest.presentation.mappers.toCharacters
import com.s21.movietest.presentation.mappers.toMovieViewModel
import com.s21.movietest.presentation.mappers.toPersonViewData
import com.s21.movietest.presentation.models.CharactersViewData
import com.s21.movietest.presentation.models.MovieViewData
import com.s21.movietest.presentation.models.PersonViewData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivityViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getPersonByFilmUseCase: GetPersonByFilmUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieViewData>>()
    val movies: LiveData<List<MovieViewData>> = _movies

    private val _persons = MutableLiveData<List<PersonViewData>>()
    val persons: LiveData<List<PersonViewData>> = _persons

    private val _personsList = MutableLiveData<CharactersViewData>()
    val personsList : LiveData<CharactersViewData> = _personsList

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun getPersons(){
        try {
            viewModelScope.launch {
                val chars : Characters? = personsList.value?.toCharacters()
                if (chars == null){
                    _error.value = "Не удалось получить список персонажей"
                    throw IllegalArgumentException("Не удалось получить список персонажей")
                }
                _persons.value =  getPersonByFilmUseCase
                    .execute(chars).map { it.toPersonViewData() }
            }
        } catch (e: HttpException) {
            _error.value = "Ошибка сети: ${e.message()}"
        } catch (e: IOException) {
            _error.value = "Ошибка ввода-вывода: ${e.message}"
        } catch (e: Exception) {
            _error.value = "Не удалось загрузить данные: ${e.message}"
        }
    }
    fun setPersonsList(charactersViewData : CharactersViewData){
        _personsList.value = charactersViewData
    }

    fun getAllMovies()  {
        try {
            viewModelScope.launch {
                _movies.value =  getAllMoviesUseCase.execute().map { it.toMovieViewModel() }
            }
        } catch (e: HttpException) {
            _error.value = "Ошибка сети: ${e.message()}"
        } catch (e: IOException) {
            _error.value = "Ошибка ввода-вывода: ${e.message}"
        } catch (e: Exception) {
            _error.value = "Не удалось загрузить данные: ${e.message}"
        }
    }

    fun onErrorShown() {
        _error.value = null
    }

}