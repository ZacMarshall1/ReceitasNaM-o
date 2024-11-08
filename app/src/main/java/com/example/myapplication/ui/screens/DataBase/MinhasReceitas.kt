package com.example.myapplication.ui.screens.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class MinhaReceita(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val titulo: String,
    val descricao: String,
    val ingredientes: String,
    val preparo: String
)

class ReceitaADM(
    id: Int? = null,
    titulo: String,
    descricao: String,
    ingredientes: String,
    preparo: String,
    val nomeAdministrador: String
) : MinhaReceita(id, titulo, descricao, ingredientes, preparo)
