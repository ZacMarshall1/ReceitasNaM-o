package com.example.myapplication.ui.screens.receitas

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetalhesReceitaScreen(recipeId: Int?, image: Bitmap?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detalhes da Receita $recipeId",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Espaço para a imagem da receita
        image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Imagem da Receita",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        } ?: run {
            Text(
                text = "Imagem não disponível",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Descrição da receita
        Text("Descrição: Aqui vai a descrição detalhada da receita", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredientes
        Text(
            text = "Ingredientes:",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge
        )
        // Liste os ingredientes aqui

        Spacer(modifier = Modifier.height(16.dp))

        // Modo de preparo
        Text(
            text = "Modo de Preparo:",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge
        )
        // Adicione o modo de preparo aqui

        Spacer(modifier = Modifier.height(16.dp))

        // Qualquer outro detalhe, como tempo de preparo, rendimento, etc.
    }
}
