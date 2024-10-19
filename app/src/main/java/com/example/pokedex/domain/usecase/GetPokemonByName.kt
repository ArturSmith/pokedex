package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.repository.PokemonRepository

class GetPokemonByName(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name:String): Pokemon {
        return pokemonRepository.getPokemonByName(name)
    }
}