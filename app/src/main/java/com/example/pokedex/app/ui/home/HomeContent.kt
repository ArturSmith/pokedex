@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pokedex.app.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Bookmark
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
            FAButton { onChoosePokemonClicked() }
        },
        topBar = {
            TopBar{onBookmarkClicked()}
        },
        containerColor = Color.Transparent

    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
            PokemonCard(
                pokemon = item,
                isMarkButtonVisible = false,
                onMark = {}
            ) {
                onPokemonClicked(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onBookmarkClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.pokedex_collection),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {
                onBookmarkClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun FAButton(
    onChoosePokemonClicked: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    IconButton(
        modifier = Modifier
            .size(70.dp)
            .scale(scale),
        onClick = onChoosePokemonClicked
    ) {
        Icon(
            painter = painterResource(R.drawable.pokemon_egg),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}




