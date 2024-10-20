package com.example.pokedex.app.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState(emptyList(), 0))
    val state = _state.asStateFlow()

}