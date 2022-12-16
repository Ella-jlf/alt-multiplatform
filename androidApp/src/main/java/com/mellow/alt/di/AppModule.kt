package com.mellow.alt.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        NetworkModule::class,
        ActivitiesInjectorModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}