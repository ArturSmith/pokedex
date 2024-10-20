package com.example.pokedex.app.ui.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.R
import com.example.pokedex.di.ApplicationComponent

@Composable
fun PokemonContent(
    id: Int,
    component: ApplicationComponent,
    onBackPressed: () -> Unit,
) {
    val viewModel: PokemonViewModel = viewModel(factory = component.getViewModelFactory())
    viewModel.loadData(id)
    val state by viewModel.state.collectAsState()
    val isMarked by viewModel.isMarked.collectAsState()
    val isInCollection by viewModel.isInCollection.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                isMarked = isMarked,
                onBackPressed = onBackPressed,
                onMark = {
                    viewModel.onMark()
                }
            )
        },
        floatingActionButton = {
            if (!isInCollection) {
                Button(
                    modifier = Modifier.width(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(10),
                    onClick = {
                        viewModel.collect {
                            onBackPressed()
                        }
                    },
                ) {
                    Text(text = stringResource(R.string.collect), color = Color.White)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                PokemonState.Error -> {
                    IconButton({
                        viewModel.loadData(id)
                    }) {
                        Text(stringResource(R.string.cant_load_pokemon))
                    }
                }

                PokemonState.Loading -> {
                    CircularProgressIndicator(color = Color.Black)
                }

                is PokemonState.Pokemon -> {
                    PokemonState(currentState)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PokemonState(
    state: PokemonState.Pokemon,
) {
    val pokemon = state.pokemonFull
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                GlideImage(
                    model = pokemon.frontDefaultSprite,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f) // Делаем изображение квадратным
                )
                GlideImage(
                    model = pokemon.backDefaultSprite,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f) // Делаем изображение квадратным
                )
            }
        }
        item {
            Divider(
                thickness = 5.dp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(15.dp))
        }
        item {
            Text(
                text = pokemon.name.uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(15.dp))
        }
        item {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                CustomText(
                    title = stringResource(R.string.experience),
                    value = pokemon.experience.toString()
                )
                CustomText(
                    title = stringResource(R.string.height),
                    value = pokemon.height.toString()
                )

                CustomText(
                    title = stringResource(R.string.weight),
                    value = pokemon.weight.toString()
                )

                CustomText(
                    title = stringResource(R.string.gender_rate),
                    value = pokemon.genderRate.toString()
                )

                CustomText(
                    title = stringResource(R.string.base_happiness),
                    value = pokemon.baseHappiness.toString()
                )

                CustomText(
                    title = stringResource(R.string.color),
                    value = pokemon.color
                )
                val isLegendary =
                    if (pokemon.isLegendary) R.string.yes else R.string.no
                CustomText(
                    title = stringResource(R.string.is_legendary),
                    value = stringResource(isLegendary)
                )
                CustomText(
                    title = stringResource(R.string.shape),
                    value = pokemon.shape
                )
                CustomText(
                    title = stringResource(R.string.generation),
                    value = pokemon.generation
                )
            }
        }

        item {
            Spacer(Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.evolution).uppercase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(15.dp))
        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(
                    items = List(5) {}
                ) {
                    EvolutionCard()
                }
            }
        }
    }
}

@Composable
private fun EvolutionCard() {
    Box(
        modifier = Modifier
            .background(color = Color.Gray, shape = RoundedCornerShape(size = 10.dp))
            .width(100.dp)
            .height(100.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.QuestionMark,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CustomText(
    title: String,
    value: String,
) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (titleRef, valueRef) = createRefs()

        Text(
            text = title,
            color = Color.Gray,
            modifier = Modifier.constrainAs(titleRef) {
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

        Text(
            text = value.uppercase(),
            fontSize = 15.sp,
            color = Color.Red,
            modifier = Modifier.constrainAs(valueRef) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    isMarked: Boolean,
    onBackPressed: () -> Unit,
    onMark: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { },
        navigationIcon = {
            IconButton({
                onBackPressed()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton({
                onMark()
            }) {
                Icon(
                    Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = if (isMarked) Color.Yellow else Color.Black
                )
            }
        }
    )
}