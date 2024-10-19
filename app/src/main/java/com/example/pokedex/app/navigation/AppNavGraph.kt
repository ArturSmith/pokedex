package com.example.pokedex.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    bookmarksScreenContent: @Composable () -> Unit,
    searchScreenContent: @Composable () -> Unit,
    choosePokemonScreenContent: @Composable () -> Unit,
    pokemonScreenContent: @Composable (id: Int) -> Unit,
) {

    NavHost(
        navHostController,
        Screen.Home.route
    ) {
        composable(Screen.HOME_ROUTE) {
            homeScreenContent()
        }

        composable(Screen.BOOKMARKS_ROUTE) {
            bookmarksScreenContent()
        }

        composable(Screen.SEARCH_ROUTE) {
            searchScreenContent()
        }

        composable(Screen.CHOOSE_POKEMON_ROUTE) {
            choosePokemonScreenContent()
        }

        composable(
            route = Screen.POKEMON_ROUTE,
            arguments = listOf(
                navArgument(Screen.POKEMON_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt(Screen.POKEMON_KEY)
                ?: throw RuntimeException("Args is null")
            pokemonScreenContent(id)
        }
    }
}