package com.example.pokedex.app.ui.search

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
fun SearchContent(
    component: ApplicationComponent,
    onBackPressed: () -> Unit,
    onPokemonClicked: (id: Int) -> Unit
) {
    val viewModel: SearchViewModel = viewModel(factory = component.getViewModelFactory())

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Search")
        }
    }
}