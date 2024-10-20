package com.example.pokedex.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chosen_pokemons")
data class ChosenPokemonDbModel(
    @PrimaryKey val id: Int
)
