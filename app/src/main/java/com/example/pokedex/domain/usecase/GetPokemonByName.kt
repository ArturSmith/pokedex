package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonByName @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name:String): Result<Pokemon> {
        return pokemonRepository.getPokemonByName(name)
    }
}