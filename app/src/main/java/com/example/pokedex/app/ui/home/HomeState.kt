package com.example.pokedex.app.ui.home

import com.example.pokemon.domain.entity.Pokemon

sealed interface HomeState {
    data class Pokemons(
        val pokemons: List<Pokemon>,
    ) : HomeState

    data object Loading : HomeState
    data object EmptyPokemons : HomeState
}