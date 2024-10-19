package com.example.pokemon.domain.entity

data class EvolutionChain(
    val speciesName: String,
    val speciesUrl:String,
    val evolvesTo: List<EvolutionChain>,
    val evolutionDetail: List<EvolutionDetail>
)
