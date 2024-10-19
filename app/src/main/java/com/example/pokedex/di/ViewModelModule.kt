package com.example.pokedex.di

import androidx.lifecycle.ViewModel
import com.example.pokedex.app.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}