package com.example.pokedex.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
fun SystemBarController() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    systemUiController.setSystemBarsColor(
        color = Color.Red,
        darkIcons = false
    )

    systemUiController.isStatusBarVisible = false

}

