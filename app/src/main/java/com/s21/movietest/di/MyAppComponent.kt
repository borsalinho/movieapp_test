package com.s21.movietest.di

import com.s21.movietest.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface MyAppComponent {
    fun inject(mainActivity: MainActivity)
}