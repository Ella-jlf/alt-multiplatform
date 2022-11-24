package com.mellow.alt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mellow.alt.presentation.screen.navigation.SwipeViewModel
import com.mellow.alt.presentation.screen.login.LogInViewModel
import com.mellow.alt.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    abstract fun bindLogInViewModel(logInViewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SwipeViewModel::class)
    abstract fun bindFavoritesViewModel(swipeViewModel: SwipeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}