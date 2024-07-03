package com.s21.movietest.di

import android.content.Context
import androidx.room.Room
import com.s21.data.implementation.MovieRepositoryImpl
import com.s21.data.implementation.PeopleRepositoryImpl
import com.s21.data.network.api.StarWarsApi
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.dao.PeopleDao
import com.s21.data.storage.database.MyAppDatabase
import com.s21.domain.repository.MovieRepository
import com.s21.domain.repository.PeopleRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    private val baseUrl = "https://swapi.dev/"

    @Singleton
    @Provides
    fun provideRetrofit() : StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMyAppDatabase(сontext: Context): MyAppDatabase {
        return Room.databaseBuilder(
            сontext,
            MyAppDatabase::class.java,
            "starwars-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(myAppDatabase: MyAppDatabase): MovieDao {
        return myAppDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun providePeopleDao(myAppDatabase: MyAppDatabase): PeopleDao {
        return myAppDatabase.peopleDao()
    }

    @Singleton
    @Provides
    fun provideMovieRepositoryImpl(
        starWarsApi: StarWarsApi,
        movieDao: MovieDao
    ) : MovieRepository {
        return MovieRepositoryImpl(
            starWarsApi = starWarsApi,
            movieDao = movieDao
        )
    }

    @Singleton
    @Provides
    fun providePeopleRepositoryImpl(
        starWarsApi: StarWarsApi,
        peopleDao: PeopleDao
    ) : PeopleRepository {
        return PeopleRepositoryImpl(
            starWarsApi = starWarsApi,
            peopleDao = peopleDao
        )
    }


}