package com.example.pokemon.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val speciesName: String,
    val speciesUrl: String,
    val fontDefaultSprite: String,
    val backDefaultSprite: String
)
