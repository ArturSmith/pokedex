package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository

class GetEvolutionChain(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): EvolutionChain {
        return pokemonRepository.getEvolutionChain(id)
    }
}