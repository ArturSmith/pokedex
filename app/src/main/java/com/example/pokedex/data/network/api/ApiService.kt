package com.example.pokedex.data.network.api

import com.example.pokedex.data.network.dto.PokemonListResponseDto
import com.example.pokedex.data.network.dto.PokemonResponseDto
import com.example.pokedex.data.network.dto.SpeciesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int,
    ): PokemonResponseDto

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String,
    ): PokemonResponseDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpeciesById(
        @Path("id") id: Int,
    ): SpeciesResponseDto

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpeciesByName(
        @Path("name") name: String,
    ): SpeciesResponseDto

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponseDto
}
