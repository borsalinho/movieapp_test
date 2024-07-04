package com.s21.movietest.di

import com.s21.domain.repository.MovieRepository
import com.s21.domain.repository.PersonRepository
import com.s21.domain.usecases.GetAllMoviesUseCase
import com.s21.domain.usecases.GetPersonByFilmUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideLoadAllMoviesUseCase(movieRepository: MovieRepository) : GetAllMoviesUseCase {
        return GetAllMoviesUseCase(movieRepository = movieRepository)
    }

    @Singleton
    @Provides
    fun provideGetPeoplesByFilmUseCase(personRepository: PersonRepository) : GetPersonByFilmUseCase {
        return GetPersonByFilmUseCase(personRepository = personRepository)
    }


}