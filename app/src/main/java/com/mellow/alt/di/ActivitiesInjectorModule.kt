package com.mellow.alt.di

import com.mellow.alt.presentation.screen.basic.swipe.SwipeActivity
import com.mellow.alt.presentation.screen.login.LogInActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesInjectorModule {

    @ContributesAndroidInjector(
        modules = [
            NavigationFragmentsModule::class
        ]
    )
    abstract fun contributeNavigationActivity(): SwipeActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashScreenActivity(): LogInActivity
}

@Module
abstract class NavigationFragmentsModule