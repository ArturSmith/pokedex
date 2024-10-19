package com.example.pokedex.data.network.mappers

import com.example.pokedex.data.network.models.ChainLinkDto
import com.example.pokedex.data.network.models.EvolutionChainResponseDto
import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.EvolutionDetail


fun EvolutionChainResponseDto.toEntity(): EvolutionChain {
    return chain.toEntity()
}


private fun ChainLinkDto.toEntity(): EvolutionChain {
    return EvolutionChain(
        speciesDto.name,
        speciesDto.url,
        evolvesTo.map { it.toEntity() },
        evolutionDetailDto.map {
            EvolutionDetail(
                it.minLevel ?: -1,
                it.itemDto?.name ?: "",
                it.itemDto?.url ?: ""
            )
        }
    )
}