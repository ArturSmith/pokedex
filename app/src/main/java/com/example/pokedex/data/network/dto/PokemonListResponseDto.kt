package com.example.pokedex.data.network.dto

import com.google.gson.annotations.SerializedName

data class PokemonListResponseDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<PokemonListResultDto>
)

data class PokemonListResultDto(
    @SerializedName("name")
    val name: String
)
