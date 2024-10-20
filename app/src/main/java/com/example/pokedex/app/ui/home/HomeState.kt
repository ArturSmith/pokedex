package com.example.pokedex.app.ui.home

import com.example.pokemon.domain.entity.Pokemon

data class HomeState(
    val pokemons: List<Pokemon>,
    val countOfBookmarkedPokemons: Int
)