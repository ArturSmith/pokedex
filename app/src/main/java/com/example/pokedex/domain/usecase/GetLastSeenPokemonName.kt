package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetLastSeenPokemonName @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(): String {
        return repository.getLastSeenPokemonName()
    }

}