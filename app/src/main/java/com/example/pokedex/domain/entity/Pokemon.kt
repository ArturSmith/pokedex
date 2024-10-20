package com.example.pokemon.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val experience: Int,
    val height: Int,
    val weight: Int,
    val frontDefaultSprite: String,
    val backDefaultSprite: String
)
