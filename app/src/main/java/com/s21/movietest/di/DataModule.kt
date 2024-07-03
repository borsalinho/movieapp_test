package com.s21.movietest.di

import android.content.Context
import androidx.room.Room
import com.s21.data.implementation.MovieRepositoryImpl
import com.s21.data.network.api.MoviesApi
import com.s21.data.storage.dao.MovieDao
import com.s21.data.storage.database.MyAppDatabase
import com.s21.data.storage.model.MovieEntity
import com.s21.domain.repository.MovieRepository
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
    fun provideRetrofit() : MoviesApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMyAppDatabase(сontext: Context): MyAppDatabase {
        return Room.databaseBuilder(
            сontext,
            MyAppDatabase::class.java,
            "translator-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTranslationDao(myAppDatabase: MyAppDatabase): MovieDao {
        return myAppDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideMovieRepositoryImpl(
        moviesApi: MoviesApi,
        movieDao: MovieDao
    ) : MovieRepository {
        return MovieRepositoryImpl(
            moviesApi = moviesApi,
            movieDao = movieDao
        )
    }
}