package com.s21.movietest.di

import com.s21.domain.repository.MovieRepository
import com.s21.domain.usecases.LoadAllMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideLoadAllMoviesUseCase(movieRepository: MovieRepository) : LoadAllMoviesUseCase {
        return LoadAllMoviesUseCase(movieRepository = movieRepository)
    }
}