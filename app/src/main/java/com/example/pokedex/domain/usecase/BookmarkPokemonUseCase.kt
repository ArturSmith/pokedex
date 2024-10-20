package com.example.pokedex.domain.usecase

import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class BookmarkPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) {
        return pokemonRepository.bookmarkPokemon(id)
    }
}