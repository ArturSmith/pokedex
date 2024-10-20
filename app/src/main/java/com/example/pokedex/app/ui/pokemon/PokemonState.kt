package com.example.pokedex.app.ui.pokemon

import com.example.pokedex.domain.entity.PokemonFull

sealed interface PokemonState {
    data object Loading : PokemonState
    data object Error : PokemonState
    data class Pokemon(
        val pokemonFull: PokemonFull,
        val isMarked: Boolean,
        val isInCollection: Boolean,
    ) : PokemonState
}