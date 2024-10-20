package com.example.pokedex.domain.entity

sealed interface PokemonIdentifier {
    data class Id(val id: Int) : PokemonIdentifier
    data class Name(val name: String) : PokemonIdentifier
}
