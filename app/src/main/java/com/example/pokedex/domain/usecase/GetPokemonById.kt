package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonById @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<Pokemon> {
        return pokemonRepository.getPokemonById(id)
    }
}