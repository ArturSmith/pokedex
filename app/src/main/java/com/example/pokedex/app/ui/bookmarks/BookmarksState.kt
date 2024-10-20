package com.example.pokedex.app.ui.bookmarks

import com.example.pokemon.domain.entity.Pokemon

sealed interface BookmarksState {
    data object Loading : BookmarksState
    data object EmptyList : BookmarksState
    data class Pokemons(
        val pokemons: List<Pokemon>,
    ) : BookmarksState
}