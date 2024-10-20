package com.example.pokemon.domain.repository

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonById(id: Int): Result<Pokemon>

    suspend fun getPokemonByName(name: String): Result<Pokemon>

    suspend fun getPokemonSpeciesById(id: Int): Result<PokemonSpecies>


    suspend fun getPokemonSpeciesByName(name: String): Result<PokemonSpecies>

    suspend fun getPokemonList(): Result<PokemonList>

    suspend fun bookmarkPokemon(id: Int)

    suspend fun unBookmarkPokemon(id: Int)

    suspend fun choosePokemon(id: Int)

    fun observeBookmarkedPokemons(): Flow<List<Pokemon?>>

    fun observeChosenPokemons(): Flow<List<Pokemon?>>

    fun setLastSeenPokemonName(name: String)

    fun getLastSeenPokemonName(): String
}