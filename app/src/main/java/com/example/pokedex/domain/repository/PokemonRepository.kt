package com.example.pokemon.domain.repository

import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): Result<Pokemon>

    suspend fun getPokemonByName(name: String): Result<Pokemon>

    suspend fun getPokemonSpeciesById(id: Int): Result<PokemonSpecies>

    suspend fun getPokemonSpeciesByName(name: String): Result<PokemonSpecies>

    suspend fun getEvolutionChain(id: Int): Result<EvolutionChain>

    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonList>

    suspend fun bookmarkPokemon(id: Int)

    suspend fun unBookmarkPokemon(id: Int)

    suspend fun choosePokemon(id: Int)

    fun observeBookmarkedPokemons(): Flow<List<Pokemon?>>

    fun observeChosenPokemons(): Flow<List<Pokemon?>>
}