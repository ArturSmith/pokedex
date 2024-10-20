package com.example.pokedex.app.ui.bookmarks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.usecase.ObserveBookmarkedPokemonsUseCase
import com.example.pokedex.domain.usecase.UnBookmarkPokemonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val observeBookmarkedPokemonsUseCase: ObserveBookmarkedPokemonsUseCase,
    private val unBookmarkPokemonUseCase: UnBookmarkPokemonUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<BookmarksState>(BookmarksState.Loading)
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            observeBookmarkedPokemonsUseCase()
                .map {
                    it.filterNotNull()
                }
                .collect {
                    if (it.isEmpty()){
                        _state.emit(BookmarksState.EmptyList)
                    } else {
                        _state.emit(
                            BookmarksState.Pokemons(it)
                        )
                    }
                }
        }
    }

    fun onMark(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                unBookmarkPokemonUseCase(id)
            } catch (e: Exception) {
                Log.d("BookmarksViewModel", e.message.toString())
            }
        }
    }
}