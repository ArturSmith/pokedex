package com.example.pokedex.domain.entity

data class PokemonFull(
    val id: Int,
    val name: String,
    val experience: Int,
    val height: Int,
    val weight: Int,
    val frontDefaultSprite: String,
    val backDefaultSprite: String,
    val genderRate: Int,
    val baseHappiness: Int,
    val color: String,
    val isLegendary: Boolean,
    val shape: String,
    val generation: String
)
