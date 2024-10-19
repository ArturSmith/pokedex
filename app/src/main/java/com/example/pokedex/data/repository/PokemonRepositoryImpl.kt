package com.example.pokedex.data.repository

import com.example.pokedex.data.network.api.ApiService
import com.example.pokedex.data.network.mappers.toEntity
import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRepository {
    override suspend fun getPokemonById(id: Int): Pokemon {
        return apiService.getPokemon(id).toEntity()
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        return apiService.getPokemonByName(name).toEntity()
    }

    override suspend fun getPokemonSpeciesById(id: Int): PokemonSpecies {
        return apiService.getPokemonSpecies(id).toEntity()
    }

    override suspend fun getPokemonSpeciesByName(name: String): PokemonSpecies {
        return apiService.getPokemonSpeciesByName(name).toEntity()
    }

    override suspend fun getEvolutionChain(id: Int): EvolutionChain {
        return apiService.getEvolutionChain(id).toEntity()
    }

    override suspend fun getPokemonList(limit: Int, offset: Int): PokemonList {
        return apiService.getPokemonList(limit, offset).toEntity()
    }
}