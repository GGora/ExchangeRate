package com.gg.exchangerate.di

import android.app.Application
import com.gg.exchangerate.MainApplication
import dagger.Binds
import dagger.Module


@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplication(application: MainApplication): Application

}