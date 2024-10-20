package com.example.pokedex.app.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.app.utils.getRandomColor
import com.example.pokemon.domain.entity.Pokemon

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: (id: Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 220.dp)
            .background(color = getRandomColor(), shape = RoundedCornerShape(10))
            .clickable {
                onClick(pokemon.id)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = pokemon.frontDefaultSprite,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(text = pokemon.name, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}