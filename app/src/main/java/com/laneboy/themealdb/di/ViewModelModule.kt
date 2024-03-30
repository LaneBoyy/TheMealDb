package com.laneboy.themealdb.di

import androidx.lifecycle.ViewModel
import com.laneboy.themealdb.presentation.main_menu.MainMenuViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    fun bindMainMenuViewModel(viewModel: MainMenuViewModel): ViewModel

}
