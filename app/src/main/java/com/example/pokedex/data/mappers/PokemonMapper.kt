package com.example.pokedex.data.mappers

import com.example.pokedex.data.network.dto.PokemonListResponseDto
import com.example.pokedex.data.network.dto.PokemonResponseDto
import com.example.pokedex.data.network.dto.SpeciesResponseDto
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies

fun PokemonResponseDto.toEntity(): Pokemon {
    return Pokemon(
        id = id ?: 0,
        name = name ?: "",
        experience = experience ?: 0,
        height = height ?: 0,
        weight = weight ?: 0,
        frontDefaultSprite = spritesDto?.frontDefault ?: "",
        backDefaultSprite = spritesDto?.backDefault ?: ""
    )
}

fun PokemonListResponseDto.toEntity(): PokemonList {
    return PokemonList(count, results.map { it.name })
}

fun SpeciesResponseDto.toEntity(): PokemonSpecies {
    return PokemonSpecies(
        genderRate = genderRate ?: 0,
        baseHappiness = baseHappiness ?: 0,
        color = color?.color ?: "",
        isLegendary = isLegendary ?: false,
        shape = shape?.name ?: "",
        generation = generation?.name ?: ""
    )
}




