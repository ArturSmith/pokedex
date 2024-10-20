package com.example.pokedex.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.usecase.ObserveBookmarkedPokemonsUseCase
import com.example.pokedex.domain.usecase.ObserveChosenPokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val observeChosenPokemonsUseCase: ObserveChosenPokemonsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            observeChosenPokemonsUseCase().collect {
                val pokemons = it.filterNotNull()
                if (pokemons.isEmpty()) {
                    _state.emit(HomeState.EmptyPokemons)
                } else {
                    _state.emit(HomeState.Pokemons(pokemons))
                }
            }
        }
    }


}