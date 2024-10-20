package com.example.pokedex.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.data.local.model.BookmarkedPokemonDbModel
import com.example.pokedex.data.local.model.ChosenPokemonDbModel

@Database(
    entities = [
        ChosenPokemonDbModel::class,
        BookmarkedPokemonDbModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase() : RoomDatabase() {

    abstract fun Dao(): Dao

    companion object {
        private const val NAME = "PokemonDatabase"
        private var INSTANCE: PokemonDatabase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): PokemonDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = PokemonDatabase::class.java,
                    name = NAME
                ).build()

                INSTANCE = database
                return database
            }
        }
    }
}