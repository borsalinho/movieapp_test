package com.s21.movietest.di

import com.s21.movietest.presentation.ui.fragments.PersonsFragment
import com.s21.movietest.presentation.ui.mainactivity.MainActivity
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
    fun inject(personsFragment : PersonsFragment)
}