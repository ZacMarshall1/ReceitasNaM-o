package com.example.myapplication.ui.screens.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MinhaReceita::class], version = 1)
abstract class ReceitasDatabase : RoomDatabase() {

    abstract fun receitaDao(): ReceitasDao

    companion object {
        @Volatile
        private var INSTANCE: ReceitasDatabase? = null

        fun getInstance(context: Context): ReceitasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReceitasDatabase::class.java,
                    "receitas.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
