package com.example.pokedex.app

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.pokedex.di.ApplicationComponent
import com.example.pokedex.di.DaggerApplicationComponent

class PokedexApp : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as PokedexApp).applicationComponent
}