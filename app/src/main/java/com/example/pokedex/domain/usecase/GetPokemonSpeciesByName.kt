package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository

class GetPokemonSpeciesByName(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name:String): PokemonSpecies {
        return pokemonRepository.getPokemonSpeciesByName(name)
    }
}