package com.example.pokedex.app.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedex.di.ApplicationComponent

@Composable
fun HomeContent(
    component: ApplicationComponent,
    onBookmarkClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onChoosePokemonClicked: () -> Unit,
    onPokemonClicked: (id: Int) -> Unit
) {
    val viewModel: HomeViewModel = viewModel(factory = component.getViewModelFactory())

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Home")
        }
    }
}