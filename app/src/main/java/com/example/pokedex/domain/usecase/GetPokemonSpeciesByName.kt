package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonSpeciesByName @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name:String): Result<PokemonSpecies> {
        return pokemonRepository.getPokemonSpeciesByName(name)
    }
}