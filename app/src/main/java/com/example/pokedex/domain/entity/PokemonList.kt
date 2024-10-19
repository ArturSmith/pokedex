package com.example.pokemon.domain.entity

data class PokemonList(
    val count: Int,
    val list: List<PokemonListResult>
)
