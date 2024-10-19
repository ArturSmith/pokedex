package com.example.pokedex.app

import androidx.lifecycle.ViewModel
import com.example.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

}