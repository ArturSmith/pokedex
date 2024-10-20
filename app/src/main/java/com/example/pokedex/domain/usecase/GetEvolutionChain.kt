package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.EvolutionChain
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetEvolutionChain @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<EvolutionChain> {
        return pokemonRepository.getEvolutionChain(id)
    }
}