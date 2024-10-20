package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonSpeciesById @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<PokemonSpecies> {
        return pokemonRepository.getPokemonSpeciesById(id)
    }
}