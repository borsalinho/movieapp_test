package com.s21.movietest.presentation.ui.mainactivity

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.s21.domain.usecases.LoadAllMoviesUseCase
import com.s21.movietest.presentation.mappers.toMovieViewModel
import com.s21.movietest.presentation.models.MovieViewData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivityViewModel(
    private val loadAllMoviesUseCase: LoadAllMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieViewData>>()
    val movies: LiveData<List<MovieViewData>> = _movies

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun loadAllMovies()  {
        try {
            viewModelScope.launch {
                _movies.value =  loadAllMoviesUseCase.execute().map { it.toMovieViewModel() }
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