package com.example.pokedex.data.network.models

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val spritesDto: SpritesDto?,
    @SerializedName("species")
    val speciesDto: SpeciesDto?
)

data class SpritesDto(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("back_default")
    val backDefault: String?
)

data class SpeciesDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)