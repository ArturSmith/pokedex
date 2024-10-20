package com.example.pokedex.data.network.dto

import com.google.gson.annotations.SerializedName

data class SpeciesResponseDto(
    @SerializedName("gender_rate")
    val genderRate: Int?,
    @SerializedName("base_happiness")
    val baseHappiness: Int?,
    @SerializedName("color")
    val color: SpeciesColorDto?,
    @SerializedName("is_legendary")
    val isLegendary: Boolean?,
    @SerializedName("shape")
    val shape: SpeciesShapeDto?,
    @SerializedName("generation")
    val generation: SpeciesGenerationDto?
)

data class SpeciesColorDto(
    @SerializedName("color")
    val color: String?
)

data class SpeciesShapeDto(
    @SerializedName("name")
    val name: String?
)

data class SpeciesGenerationDto(
    @SerializedName("name")
    val name: String?
)
