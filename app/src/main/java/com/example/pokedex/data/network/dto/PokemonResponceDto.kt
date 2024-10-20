package com.example.pokedex.data.network.dto

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("base_experience")
    val experience: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("weight")
    val weight: Int?,
    @SerializedName("sprites")
    val spritesDto: SpritesDto?,
)

data class SpritesDto(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("back_default")
    val backDefault: String?
)

