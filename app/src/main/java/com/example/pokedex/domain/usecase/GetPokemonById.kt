package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.repository.PokemonRepository

class GetPokemonById(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Pokemon {
        return pokemonRepository.getPokemonById(id)
    }
}