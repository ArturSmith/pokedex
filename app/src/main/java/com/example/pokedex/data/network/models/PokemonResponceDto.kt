package com.example.pokedex.data.network.models

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    val id: Int,
    val name: String,
    val spritesDto: SpritesDto,
    val speciesDto: SpeciesDto
)

data class SpritesDto(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("back_default")
    val backDefault: String?
)

data class SpeciesDto(
    val name: String,
    val url: String
)