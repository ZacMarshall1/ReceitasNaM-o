package com.example.myapplication.ui.screens.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minha_receita")
data class MinhaReceita(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val titulo: String,
    val descricao: String,
    val ingredientes: String,
    val preparo: String,
    val tipoReceita: String = "NORMAL"
)


