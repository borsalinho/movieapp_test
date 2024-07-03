package com.s21.movietest.di

import android.content.Context
import com.s21.domain.usecases.LoadAllMoviesUseCase
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
        loadAllMoviesUseCase : LoadAllMoviesUseCase
    ) : MainActivityViewModel{
        return MainActivityViewModel(
            loadAllMoviesUseCase = loadAllMoviesUseCase
        )
    }

}