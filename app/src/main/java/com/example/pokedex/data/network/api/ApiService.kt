package com.example.pokedex.data.network.api

import com.example.pokedex.data.network.models.EvolutionChainResponseDto
import com.example.pokedex.data.network.models.PokemonListResponseDto
import com.example.pokedex.data.network.models.PokemonResponseDto
import com.example.pokedex.data.network.models.PokemonSpeciesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonResponseDto

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonResponseDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ): PokemonSpeciesResponseDto

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpeciesByName(
        @Path("name") name: String
    ): PokemonSpeciesResponseDto

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: Int
    ): EvolutionChainResponseDto

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponseDto
}
