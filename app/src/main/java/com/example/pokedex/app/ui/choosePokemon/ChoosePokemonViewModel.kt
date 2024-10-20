package com.example.pokedex.app.ui.choosePokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.usecase.BookmarkPokemonUseCase
import com.example.pokedex.domain.usecase.ChoosePokemonUseCase
import com.example.pokedex.domain.usecase.GetLastSeenPokemonName
import com.example.pokedex.domain.usecase.GetPokemonByName
import com.example.pokedex.domain.usecase.GetPokemonList
import com.example.pokedex.domain.usecase.ObserveBookmarkedPokemonsUseCase
import com.example.pokedex.domain.usecase.ObserveChosenPokemonsUseCase
import com.example.pokedex.domain.usecase.SetLastSeenPokemonName
import com.example.pokedex.domain.usecase.UnBookmarkPokemonUseCase
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChoosePokemonViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList,
    private val observeChosenPokemonsUseCase: ObserveChosenPokemonsUseCase,
    private val observeBookmarkedPokemonsUseCase: ObserveBookmarkedPokemonsUseCase,
    private val bookmarkPokemonUseCase: BookmarkPokemonUseCase,
    private val unBookmarkPokemonUseCase: UnBookmarkPokemonUseCase,
    private val choosePokemonUseCase: ChoosePokemonUseCase,
    private val getPokemonByName: GetPokemonByName,
    private val getLastSeenPokemonName: GetLastSeenPokemonName,
    private val setLastSeenPokemonName: SetLastSeenPokemonName,
) : ViewModel() {

    private val _state = MutableStateFlow<ChoosePokemonState>(ChoosePokemonState.Loading)
    val state = _state.asStateFlow()

    private val _filteredPokemons = MutableStateFlow<List<String>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            observeChosenPokemonsUseCase()
                .map {
                    it.filterNotNull()
                }
                .collect {
                    getPokemonList().fold(
                        onSuccess = { allPokemons ->
                            handlePokemonListSuccessResult(
                                allPokemons,
                                it
                            )
                        },
                        onFailure = {
                            Log.d("ChoosePokemonViewModel", it.message.toString())
                        }
                    )
                }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeBookmarkedPokemonsUseCase().collect {
                if (_state.value is ChoosePokemonState.RandomPokemon) {
                    val currentPokemon = _state.value as ChoosePokemonState.RandomPokemon
                    _state.emit(
                        currentPokemon.copy(
                            isMarked = it.any { it?.id == currentPokemon.pokemon.id }
                        )
                    )
                }
            }
        }
    }

    private fun handlePokemonListSuccessResult(
        allPokemons: PokemonList,
        chosenPokemons: List<Pokemon>,
    ) {
        val chosenPokemonsNames = chosenPokemons.map { it.name }

        val filteredPokemons = allPokemons
            .names
            .filterNot { chosenPokemonsNames.contains(it) }
            .toMutableList()

        _filteredPokemons.value = filteredPokemons.toList()

        setRandomPokemon()

    }

    private fun setRandomPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            _filteredPokemons.value.ifEmpty {
                _state.emit(ChoosePokemonState.EmptyList)
                return@launch
            }

            var randomName = ""
            do {
                randomName = _filteredPokemons.value.random()
            } while (randomName == getLastSeenPokemonName())

            setLastSeenPokemonName(randomName)

            getPokemonByName(randomName)
                .fold(
                    onSuccess = { pokemon ->
                        val isMarked = observeBookmarkedPokemonsUseCase()
                            .first()
                            .filterNotNull()
                            .any { it.id == pokemon.id }

                        _state.emit(
                            ChoosePokemonState.RandomPokemon(
                                pokemon = pokemon,
                                isMarked = isMarked
                            )
                        )
                    },
                    onFailure = {
                        Log.d("ChoosePokemonViewModel", it.message.toString())
                    }
                )
        }
    }

    fun onNext() {
        _state.value = ChoosePokemonState.Loading
        setRandomPokemon()
    }

    fun onCollect(popBackStack: () -> Unit) {
        viewModelScope.launch {
            (_state.value as? ChoosePokemonState.RandomPokemon)?.pokemon?.id?.let { id ->
                try {
                    withContext(Dispatchers.IO) {
                        choosePokemonUseCase(id)
                    }
                    popBackStack()
                } catch (e: Exception) {
                    Log.d("ChoosePokemonViewModel", e.message.toString())
                }
            }
        }
    }

    fun onMark(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (observeBookmarkedPokemonsUseCase().first().any { it?.id == id }) {
                    unBookmarkPokemonUseCase(id)
                } else {
                    bookmarkPokemonUseCase(id)
                }
            } catch (e: Exception) {
                Log.d("ChoosePokemonViewModel", e.message.toString())
            }

        }
    }


}