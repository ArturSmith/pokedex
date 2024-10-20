package com.example.pokedex.app.ui.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.entity.PokemonIdentifier
import com.example.pokedex.domain.usecase.BookmarkPokemonUseCase
import com.example.pokedex.domain.usecase.ChoosePokemonUseCase
import com.example.pokedex.domain.usecase.GetFullPokemon
import com.example.pokedex.domain.usecase.ObserveBookmarkedPokemonsUseCase
import com.example.pokedex.domain.usecase.ObserveChosenPokemonsUseCase
import com.example.pokedex.domain.usecase.UnBookmarkPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonViewModel @Inject constructor(
    private val getFullPokemon: GetFullPokemon,
    private val observeBookmarkedPokemonsUseCase: ObserveBookmarkedPokemonsUseCase,
    private val observeChosenPokemonsUseCase: ObserveChosenPokemonsUseCase,
    private val bookmarkPokemonUseCase: BookmarkPokemonUseCase,
    private val unBookmarkPokemonUseCase: UnBookmarkPokemonUseCase,
    private val chosePokemonUseCase: ChoosePokemonUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val state = _state.asStateFlow()

    private val _id = MutableStateFlow<Int?>(null)

    private val _isMarked = MutableStateFlow(false)
    val isMarked = _isMarked.asStateFlow()

    private val _isInCollection = MutableStateFlow(true)
    val isInCollection = _isInCollection.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            observeChosenPokemonsUseCase()
                .combine(observeBookmarkedPokemonsUseCase()) { chosen, marked ->
                    Pair(chosen.filterNotNull(), marked.filterNotNull())
                }
                .combine(_id) { pair, id ->
                    Triple(pair.first, pair.second, id)
                }
                .collect { triple ->
                    _isInCollection.emit(triple.first.any { it.id == triple.third })
                    _isMarked.emit(triple.second.any { it.id == triple.third })
                }
        }
    }

    fun loadData(id: Int) {
        _state.value = PokemonState.Loading
        _id.value = id
        viewModelScope.launch(Dispatchers.IO) {
            val isMarked =
                observeBookmarkedPokemonsUseCase().first().any { it?.id == id }
            val isInCollection =
                observeChosenPokemonsUseCase().first().any { it?.id == id }

            getFullPokemon(PokemonIdentifier.Id(id))
                .fold(
                    onSuccess = {
                        _state.emit(
                            PokemonState.Pokemon(
                                pokemonFull = it,
                                isMarked = isMarked,
                                isInCollection = isInCollection
                            )
                        )
                    },
                    onFailure = {
                        _state.emit(
                            PokemonState.Error
                        )
                    }
                )
        }
    }

    fun onMark() {
        viewModelScope.launch(Dispatchers.IO) {
            _id.value?.let {
                try {
                    if (_isMarked.value) {
                        unBookmarkPokemonUseCase(it)
                    } else {
                        bookmarkPokemonUseCase(it)
                    }
                } catch (e: Error) {
                    Log.d("PokemonViewModel", e.message.toString())
                }
            }
        }
    }

    fun collect(popBackStack: () -> Unit) {
        viewModelScope.launch {
            _id.value?.let {
                try {
                    withContext(Dispatchers.IO) {
                        chosePokemonUseCase(it)
                    }
                    popBackStack()
                } catch (e: Exception) {
                    Log.d("PokemonViewModel", e.message.toString())
                }
            }
        }
    }


}