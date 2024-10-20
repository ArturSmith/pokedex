package com.example.pokedex.app.ui.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedex.R
import com.example.pokedex.app.ui.commonElements.PokemonCard
import com.example.pokedex.di.ApplicationComponent
import com.example.pokemon.domain.entity.Pokemon

@Composable
fun BookmarksContent(
    component: ApplicationComponent,
    onBackPressed: () -> Unit,
    onPokemonClicked: (id: Int) -> Unit,
) {
    val viewModel: BookmarksViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar {
                onBackPressed()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                BookmarksState.EmptyList -> {
                    Text(
                        text = stringResource(R.string.empty_favorite_list),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                BookmarksState.Loading -> {
                    CircularProgressIndicator(color = Color.Black)
                }

                is BookmarksState.Pokemons -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(
                            items = currentState.pokemons,
                        ) { item: Pokemon ->
                            PokemonCard(
                                pokemon = item,
                                isMarkButtonVisible = true,
                                onMark = {
                                    viewModel.onMark(it)
                                }
                            ) {
                                onPokemonClicked(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.bookmarks),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}