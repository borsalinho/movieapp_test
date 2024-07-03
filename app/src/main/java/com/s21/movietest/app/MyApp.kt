package com.s21.movietest.app

import android.app.Application
import com.s21.movietest.di.AppModule
import com.s21.movietest.di.DaggerMyAppComponent
import com.s21.movietest.di.DataModule
import com.s21.movietest.di.DomainModule
import com.s21.movietest.di.MyAppComponent

class MyApp : Application() {

    lateinit var myAppComponent: MyAppComponent
    override fun onCreate() {
        super.onCreate()

        myAppComponent = DaggerMyAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .build()

    }
}