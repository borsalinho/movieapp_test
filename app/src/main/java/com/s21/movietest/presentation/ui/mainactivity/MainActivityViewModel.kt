package com.s21.movietest.presentation.ui.mainactivity

import androidx.lifecycle.ViewModel
import com.s21.domain.usecases.LoadAllMoviesUseCase

class MainActivityViewModel(
    private val loadAllMoviesUseCase: LoadAllMoviesUseCase
) : ViewModel() {

    suspend fun loadAllMovies() {
        loadAllMoviesUseCase.execute()
    }
}