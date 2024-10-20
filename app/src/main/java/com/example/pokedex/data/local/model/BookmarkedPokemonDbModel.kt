package com.example.pokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_pokemons")
data class BookmarkedPokemonDbModel(
    @PrimaryKey val id: Int
)
