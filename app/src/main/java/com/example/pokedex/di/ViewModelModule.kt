package com.example.pokedex.di

import androidx.lifecycle.ViewModel
import com.example.pokedex.app.ui.bookmarks.BookmarksViewModel
import com.example.pokedex.app.ui.choosePokemon.ChoosePokemonViewModel
import com.example.pokedex.app.ui.home.HomeViewModel
import com.example.pokedex.app.ui.pokemon.PokemonViewModel
import com.example.pokedex.app.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Binds
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    @Binds
    fun bindPokemonViewModel(pokemonViewModel: PokemonViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ChoosePokemonViewModel::class)
    @Binds
    fun bindChoosePokemonViewModel(choosePokemonViewModel: ChoosePokemonViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @Binds
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @IntoMap
    @ViewModelKey(BookmarksViewModel::class)
    @Binds
    fun bindBookmarksViewModel(bookmarksViewModel: BookmarksViewModel): ViewModel
}