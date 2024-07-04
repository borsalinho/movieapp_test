package com.s21.movietest.di

import android.content.Context
import com.s21.domain.usecases.GetAllMoviesUseCase
import com.s21.domain.usecases.GetPersonByFilmUseCase
import com.s21.movietest.presentation.ui.mainactivity.MainActivityViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context : Context) {

    @Singleton
    @Provides
    fun providerContext() : Context{
        return context
    }

    @Singleton
    @Provides
    fun provideMainActivityViewModel(
        getAllMoviesUseCase : GetAllMoviesUseCase,
        getPersonByFilmUseCase: GetPersonByFilmUseCase
    ) : MainActivityViewModel{
        return MainActivityViewModel(
            getAllMoviesUseCase = getAllMoviesUseCase,
            getPersonByFilmUseCase = getPersonByFilmUseCase
        )
    }

}