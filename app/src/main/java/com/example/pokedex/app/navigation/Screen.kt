package com.example.pokedex.app.navigation

sealed class Screen(val route: String) {

    object Home : Screen(HOME_ROUTE)
    object Bookmarks : Screen(BOOKMARKS_ROUTE)
    object ChoosePokemon : Screen(CHOOSE_POKEMON_ROUTE)
    object Search : Screen(SEARCH_ROUTE)

    object Pokemon : Screen(POKEMON_ROUTE) {
        private const val ROUTE_FOR_ARGS = "pokemon"

        fun getRouteWithArgs(pokemonId: Int): String {
            return "$ROUTE_FOR_ARGS/${pokemonId}"
        }
    }

    companion object {
        const val POKEMON_KEY = "pokemon_key"
        const val HOME_ROUTE = "home"
        const val BOOKMARKS_ROUTE = "bookmarks"
        const val POKEMON_ROUTE = "pokemon/{$POKEMON_KEY}"
        const val CHOOSE_POKEMON_ROUTE = "choosePokemon"
        const val SEARCH_ROUTE = "search"
    }
}
