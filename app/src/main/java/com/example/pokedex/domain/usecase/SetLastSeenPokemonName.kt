package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class SetLastSeenPokemonName @Inject constructor(
    private val repository: PokemonRepository,
) {
    operator fun invoke(name: String) {
        return repository.setLastSeenPokemonName(name)
    }

}