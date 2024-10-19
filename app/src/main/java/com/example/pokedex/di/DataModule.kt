package com.example.pokedex.di

import com.example.pokedex.data.network.api.ApiFactory
import com.example.pokedex.data.network.api.ApiService
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokemon.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindPokemonRepository(repositoryImpl: PokemonRepositoryImpl): PokemonRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiClient(): ApiService {
            return ApiFactory.apiService
        }
    }
}