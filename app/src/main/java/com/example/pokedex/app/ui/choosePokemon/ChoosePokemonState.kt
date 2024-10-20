package com.example.pokedex.app.ui.choosePokemon

import androidx.compose.ui.graphics.Color
import com.example.pokedex.app.utils.getRandomColor
import com.example.pokemon.domain.entity.Pokemon


sealed interface ChoosePokemonState {
    data object Loading : ChoosePokemonState
    data class RandomPokemon(
        val pokemon: Pokemon,
        val isMarked: Boolean,
        val color: Color = getRandomColor()
    ) : ChoosePokemonState

    data object EmptyList : ChoosePokemonState
}