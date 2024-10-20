package com.example.pokedex.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.pokedex.R
import com.example.pokedex.app.getApplicationComponent
import com.example.pokedex.app.navigation.AppNavGraph
import com.example.pokedex.app.navigation.Screen
import com.example.pokedex.app.navigation.rememberNavigationState
import com.example.pokedex.app.ui.bookmarks.BookmarksContent
import com.example.pokedex.app.ui.choosePokemon.ChoosePokemonContent
import com.example.pokedex.app.ui.home.HomeContent
import com.example.pokedex.app.ui.pokemon.PokemonContent
import com.example.pokedex.app.ui.search.SearchContent
import com.example.pokedex.app.ui.theme.PokedexTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SystemBarController()
            val component = getApplicationComponent()
            val navHostController = rememberNavigationState()
            PokedexTheme {
                AppContent {
                    AppNavGraph(
                        navHostController = navHostController.navHostController,
                        homeScreenContent = {
                            HomeContent(
                                component = component,
                                onBookmarkClicked = {
                                    navHostController.navigateTo(Screen.BOOKMARKS_ROUTE)
                                },
                                onSearchClicked = {
                                    navHostController.navigateTo(Screen.SEARCH_ROUTE)
                                },
                                onChoosePokemonClicked = {
                                    navHostController.navigateTo(Screen.CHOOSE_POKEMON_ROUTE)
                                },
                                onPokemonClicked = { id ->
                                    navHostController.navigateToPokemon(id)
                                }
                            )
                        },
                        bookmarksScreenContent = {
                            BookmarksContent(
                                component = component,
                                onBackPressed = {
                                    navHostController.navHostController.popBackStack()
                                },
                                onPokemonClicked = { id ->
                                    navHostController.navigateToPokemon(id)
                                }
                            )
                        },
                        searchScreenContent = {
                            SearchContent(
                                component = component,
                                onBackPressed = {
                                    navHostController.navHostController.popBackStack()
                                },
                                onPokemonClicked = { id ->
                                    navHostController.navigateToPokemon(id)
                                }
                            )
                        },
                        choosePokemonScreenContent = {
                            ChoosePokemonContent(
                                component = component,
                                onBackPressed = {
                                    navHostController.navHostController.popBackStack()
                                },
                                onPokemonClicked = { id ->
                                    navHostController.navigateToPokemon(id)
                                }
                            )
                        },
                        pokemonScreenContent = { id ->
                            PokemonContent(
                                id = id,
                                component = component,
                                onBackPressed = {
                                    navHostController.navHostController.popBackStack()
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AppContent(content: @Composable () -> Unit) {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.pokedex_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            content()
        }
    }
}

@Composable
private fun SystemBarController() {
    val systemUiController = rememberSystemUiController()

    systemUiController.setNavigationBarColor(
        color = Color.Red,
        darkIcons = false
    )
    systemUiController.setSystemBarsColor(Color.Red)
    systemUiController.isStatusBarVisible = false
}

