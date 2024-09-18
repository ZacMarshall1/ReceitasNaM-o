package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ScreenOneA(padding: PaddingValues) {

    var afazeres = mutableListOf(
        Afazer(
            titulo = "Comprar um carro",
            descricao = "Visitar concessionárias",
            id = 1
        ),
        Afazer(
            titulo = "Lavar roupas",
            descricao = "Lavar roupas pela manhã",
            id = 2
        )
    )


    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items( afazeres ){ afazer ->
            Text(
                text = afazer.titulo,
                Modifier.padding(padding),
                fontSize = 40.sp
            )
        }
    }
}

data class Afazer(
    var titulo: String,
    var descricao: String,
    var concluido: Boolean = false,
    var id: Int? = null
)