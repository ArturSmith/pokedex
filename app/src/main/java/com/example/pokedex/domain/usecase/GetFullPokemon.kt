package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.entity.PokemonFull
import com.example.pokedex.domain.entity.PokemonIdentifier
import com.example.pokedex.domain.extensions.flatMap
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetFullPokemon @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(identifier: PokemonIdentifier): Result<PokemonFull> {
        return getPokemon(identifier).flatMap { pokemon ->
            getPokemonSpecies(identifier).map { species ->
                PokemonFull(
                    id = pokemon.id,
                    name = pokemon.name,
                    experience = pokemon.experience,
                    height = pokemon.height,
                    weight = pokemon.weight,
                    frontDefaultSprite = pokemon.frontDefaultSprite,
                    backDefaultSprite = pokemon.backDefaultSprite,
                    genderRate = species.genderRate,
                    baseHappiness = species.baseHappiness,
                    color = species.color,
                    isLegendary = species.isLegendary,
                    shape = species.shape,
                    generation = species.generation
                )
            }
        }
    }

    private suspend fun getPokemon(identifier: PokemonIdentifier): Result<Pokemon> {
        return when (identifier) {
            is PokemonIdentifier.Id -> repository.getPokemonById(identifier.id)
            is PokemonIdentifier.Name -> repository.getPokemonByName(identifier.name)
        }
    }

    private suspend fun getPokemonSpecies(identifier: PokemonIdentifier): Result<PokemonSpecies> {
        return when (identifier) {
            is PokemonIdentifier.Id -> repository.getPokemonSpeciesById(identifier.id)
            is PokemonIdentifier.Name -> repository.getPokemonSpeciesByName(identifier.name)
        }
    }

}