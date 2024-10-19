package com.example.pokedex.data.network.models

data class PokemonListResponseDto(
    val count: Int,
    val results: List<PokemonListResultDto>
)

data class PokemonListResultDto(
    val name: String,
    val url: String
)
