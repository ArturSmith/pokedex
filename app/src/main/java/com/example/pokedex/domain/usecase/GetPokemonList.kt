package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.repository.PokemonRepository

class GetPokemonList(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): PokemonList {
        return pokemonRepository.getPokemonList(limit, offset)
    }
}