package com.example.pokemon.domain.entity

data class PokemonSpecies(
    val genderRate: Int,
    val baseHappiness: Int,
    val color: String,
    val isLegendary: Boolean,
    val shape: String,
    val generation: String
)
