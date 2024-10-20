@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokedex.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedex.R
import com.example.pokedex.app.ui.commonElements.PokemonCard
import com.example.pokedex.di.ApplicationComponent
import com.example.pokemon.domain.entity.Pokemon

@Composable
fun HomeContent(
    component: ApplicationComponent,
    onBookmarkClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onChoosePokemonClicked: () -> Unit,
    onPokemonClicked: (id: Int) -> Unit,
) {
    val viewModel: HomeViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            IconButton(
                modifier = Modifier.size(70.dp),
                onClick = onChoosePokemonClicked
            ) {
                Icon(
                    painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        topBar = {
            HomeContentTopAppBar(onSearchClicked, onBookmarkClicked)
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.pokemon_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            when (val currentState = state) {
                HomeState.EmptyPokemons -> {
                    Text(
                        text = stringResource(R.string.empty_pokemons),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                HomeState.Loading -> {
                    CircularProgressIndicator(color = Color.Black)
                }

                is HomeState.Pokemons -> {
                    HomePokemonsState(currentState.pokemons) { onPokemonClicked(it) }
                }
            }
        }
    }
}

@Composable
private fun HomePokemonsState(
    pokemons: List<Pokemon>,
    onPokemonClicked: (id: Int) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(
            items = pokemons,
        ) { item: Pokemon ->
            PokemonCard(item) {
                onPokemonClicked(it)
            }
        }
    }
}

@Composable
private fun HomeContentTopAppBar(
    onSearchClicked: () -> Unit,
    onBookmarkClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onSearchClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onBookmarkClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}




