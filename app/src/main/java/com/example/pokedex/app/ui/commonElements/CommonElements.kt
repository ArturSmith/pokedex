package com.example.pokedex.app.ui.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokedex.app.utils.getRandomColor
import com.example.pokemon.domain.entity.Pokemon

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    isMarkButtonVisible: Boolean,
    onMark: (id: Int) -> Unit,
    onClick: (id: Int) -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 220.dp)
            .background(color = getRandomColor(), shape = RoundedCornerShape(10))
            .clickable {
                onClick(pokemon.id)
            },
    ) {
        val (bokmarkButton, image, name) = createRefs()

        GlideImage(
            model = pokemon.frontDefaultSprite,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .aspectRatio(1f)
        )

        Text(
            text = pokemon.name.uppercase(),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            })

        if (isMarkButtonVisible) {
            IconButton(
                modifier = Modifier.constrainAs(bokmarkButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
                onClick = {
                    onMark(pokemon.id)
                }
            ) {
                Icon(Icons.Default.Bookmark, contentDescription = null, tint = Color.Yellow)
            }
        }

    }
}