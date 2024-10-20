package com.example.pokedex.data.repository

import com.example.pokedex.data.local.db.Dao
import com.example.pokedex.data.local.model.BookmarkedPokemonDbModel
import com.example.pokedex.data.local.model.ChosenPokemonDbModel
import com.example.pokedex.data.mappers.toEntity
import com.example.pokedex.data.network.api.ApiService
import com.example.pokemon.domain.entity.Pokemon
import com.example.pokemon.domain.entity.PokemonList
import com.example.pokemon.domain.entity.PokemonSpecies
import com.example.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: Dao,
) : PokemonRepository {

    private val _lastSeenPokemonName = MutableStateFlow("")

    override suspend fun getPokemonById(id: Int): Result<Pokemon> {
        return try {
            val response = apiService.getPokemon(id).toEntity()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonByName(name: String): Result<Pokemon> {
        return try {
            val response = apiService.getPokemonByName(name).toEntity()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonSpeciesById(id: Int): Result<PokemonSpecies> {
        return try {
            val response = apiService.getPokemonSpeciesById(id).toEntity()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonSpeciesByName(name: String): Result<PokemonSpecies> {
        return try {
            val response = apiService.getPokemonSpeciesByName(name).toEntity()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonList(): Result<PokemonList> {
        return try {
            val response = apiService.getPokemonList().toEntity()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun bookmarkPokemon(id: Int) {
        dao.bookmarkPokemon(BookmarkedPokemonDbModel(id))
    }

    override suspend fun unBookmarkPokemon(id: Int) {
        dao.unBookmarkPokemon(id)
    }

    override suspend fun choosePokemon(id: Int) {
        dao.choosePokemon(ChosenPokemonDbModel(id))
    }

    override fun observeBookmarkedPokemons(): Flow<List<Pokemon?>> {
        return dao.observeBookmarkedPokemons().map {
            it.map {
                getPokemonById(it.id).getOrNull()
            }
        }
    }

    override fun observeChosenPokemons(): Flow<List<Pokemon?>> {
        return dao.observeChosenPokemons().map {
            it.map {
                getPokemonById(it.id).getOrNull()
            }
        }
    }

    override fun setLastSeenPokemonName(name: String) {
        _lastSeenPokemonName.value = name
    }

    override fun getLastSeenPokemonName(): String {
        return _lastSeenPokemonName.value
    }
}