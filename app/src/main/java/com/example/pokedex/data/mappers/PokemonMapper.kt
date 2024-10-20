package com.example.pokedex.data.mappers

import com.example.pokedex.data.network.models.PokemonListResponseDto
import com.example.pokedex.data.network.models.PokemonListResultDto
import com.example.pokedex.data.network.models.PokemonResponseDto
import com.example.pokedex.data.network.models.PokemonSpeciesResponseDto
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonListResult
import com.example.pokemon.domain.entity.PokemonSpecies

fun PokemonResponseDto.toEntity(): Pokemon {
    return Pokemon(
        id ?: 0,
        name ?: "",
        speciesDto?.name ?: "",
        speciesDto?.url ?: "",
        spritesDto?.frontDefault ?: "",
        spritesDto?.backDefault ?: ""
    )
}

fun PokemonListResponseDto.toEntity(): PokemonList {
    return PokemonList(count, results.map { it.toEntity() })
}

fun PokemonSpeciesResponseDto.toEntity(): PokemonSpecies {
    return PokemonSpecies(evolutionChainDto.url)
}

private fun PokemonListResultDto.toEntity(): PokemonListResult {
    return PokemonListResult(name, url)
}




