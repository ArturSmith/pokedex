package com.example.pokedex.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.local.model.BookmarkedPokemonDbModel
import com.example.pokedex.data.local.model.ChosenPokemonDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkPokemon(pokemon: BookmarkedPokemonDbModel)

    @Query("DELETE FROM bookmarked_pokemons WHERE id=:id")
    suspend fun unBookmarkPokemon(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun choosePokemon(pokemon: ChosenPokemonDbModel)

    @Query("SELECT * FROM bookmarked_pokemons")
    fun observeBookmarkedPokemons(): Flow<List<BookmarkedPokemonDbModel>>

    @Query("SELECT * FROM chosen_pokemons")
    fun observeChosenPokemons(): Flow<List<ChosenPokemonDbModel>>
}