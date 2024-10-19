package com.example.pokedex.data.network.models

import com.google.gson.annotations.SerializedName

data class EvolutionChainResponseDto(
    val chain: ChainLinkDto
)

data class ChainLinkDto(
    @SerializedName("species")
    val speciesDto: SpeciesDto,
    @SerializedName("evolves_to")
    val evolvesTo: List<ChainLinkDto>,
    @SerializedName("evolution_details")
    val evolutionDetailDto: List<EvolutionDetailDto>
)

data class EvolutionDetailDto(
    @SerializedName("min_level")
    val minLevel: Int?,
    @SerializedName("item")
    val itemDto: ItemDto?
)

data class ItemDto(
    val name: String,
    val url: String
)
