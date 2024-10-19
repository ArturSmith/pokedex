package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository

class GetPokemonSpeciesById(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): PokemonSpecies {
        return pokemonRepository.getPokemonSpeciesById(id)
    }
}