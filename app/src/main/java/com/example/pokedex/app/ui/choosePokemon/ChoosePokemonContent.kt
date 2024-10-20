package com.example.pokedex.app.ui.choosePokemon

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
fun ChoosePokemonContent(
    component: ApplicationComponent,
    onBackPressed: () -> Unit,
    onPokemonClicked: (id: Int) -> Unit,
) {
    val viewModel: ChoosePokemonViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()
    val isButtonsEnable = state is ChoosePokemonState.RandomPokemon
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopBar { onBackPressed() }
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val (mainContent, buttons) = createRefs()

            MainContent(
                modifier = Modifier
                    .constrainAs(mainContent) {
                        top.linkTo(parent.top)
                        bottom.linkTo(buttons.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                state = state,
                onPokemonClicked = {
                    onPokemonClicked(it)
                },
                onMark = {
                    viewModel.onMark(it)
                }
            )
            Buttons(
                modifier = Modifier.constrainAs(buttons) {
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                isButtonsEnable = isButtonsEnable,
                onNext = {
                    viewModel.onNext()
                },
                onCollect = {
                    viewModel.onCollect {
                        onBackPressed()
                    }
                }
            )
        }

    }
}

@Composable
private fun Buttons(
    modifier: Modifier,
    isButtonsEnable: Boolean,
    onNext: () -> Unit,
    onCollect: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(10),
            onClick = onCollect,
            enabled = isButtonsEnable
        ) {
            Text(text = stringResource(R.string.collect), color = Color.White)
        }
        Button(
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            shape = RoundedCornerShape(10),
            onClick = onNext,
            enabled = isButtonsEnable
        ) {
            Text(text = stringResource(R.string.next), color = Color.White)
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier,
    state: ChoosePokemonState,
    onPokemonClicked: (id: Int) -> Unit,
    onMark: (id: Int) -> Unit,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(350.dp)
            .clip(shape = RoundedCornerShape(10)),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ChoosePokemonState.EmptyList -> {
                Text(text = stringResource(R.string.cant_load_pokemon))
            }

            ChoosePokemonState.Loading -> {
                CircularProgressIndicator(color = Color.Black)
            }

            is ChoosePokemonState.RandomPokemon -> {
                PokemonLoadedState(state, onPokemonClicked, onMark)
            }
        }

    }
}

@Composable
private fun PokemonLoadedState(
    state: ChoosePokemonState.RandomPokemon,
    onPokemonClicked: (id: Int) -> Unit,
    onMark: (id: Int) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = state.color)
            .clickable { onPokemonClicked(state.pokemon.id) },
    ) {
        val (bookmarkButton, image, name) = createRefs()

        IconButton(
            modifier = Modifier.constrainAs(bookmarkButton) {
                top.linkTo(parent.top, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
            },
            onClick = {
                onMark(state.pokemon.id)
            }
        ) {
            Icon(
                Icons.Default.Bookmark,
                contentDescription = null,
                tint = if (state.isMarked) Color.Yellow else Color.White
            )
        }

        PokemonAnimatedImage(
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            image = state.pokemon.frontDefaultSprite
        )

        Text(
            text = state.pokemon.name.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.constrainAs(name) {
                bottom.linkTo(parent.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PokemonAnimatedImage(
    modifier: Modifier,
    image: String,
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
    GlideImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(20.dp)
            .scale(scale)
            .aspectRatio(1f)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.choose_pokemon),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}