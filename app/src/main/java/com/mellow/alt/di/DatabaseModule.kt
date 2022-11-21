package com.mellow.alt.di

import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRetrofit(application: Application): Realm {
        Realm.init(application)

        return Realm.getInstance(
            RealmConfiguration.Builder()
                .rxFactory(RealmObservableFactory(false))
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }
}