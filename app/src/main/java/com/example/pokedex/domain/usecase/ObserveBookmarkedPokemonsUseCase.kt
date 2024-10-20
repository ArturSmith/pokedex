package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBookmarkedPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon?>> {
        return pokemonRepository.observeBookmarkedPokemons()
    }
}