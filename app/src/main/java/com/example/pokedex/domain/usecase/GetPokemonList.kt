package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): Result<PokemonList> {
        return pokemonRepository.getPokemonList(limit, offset)
    }
}