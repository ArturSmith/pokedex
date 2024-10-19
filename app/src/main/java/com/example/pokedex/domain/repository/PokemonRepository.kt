package com.example.pokemon.domain.repository

import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): Pokemon

    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonSpeciesById(id: Int): PokemonSpecies

    suspend fun getPokemonSpeciesByName(name: String): PokemonSpecies

    suspend fun getEvolutionChain(id: Int): EvolutionChain

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList
}