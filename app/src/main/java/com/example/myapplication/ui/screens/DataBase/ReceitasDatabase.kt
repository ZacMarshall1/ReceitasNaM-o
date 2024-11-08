package com.example.myapplication.ui.screens.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MinhaReceita::class], version = 1)
abstract class ReceitasDatabase: RoomDatabase(){

    abstract fun receitaDao(): ReceitasDao

    companion object{
        fun abrirBancoDeDados(context: Context): ReceitasDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ReceitasDatabase::class.java, "receitas.db"
            ).build()
        }
    }
}
