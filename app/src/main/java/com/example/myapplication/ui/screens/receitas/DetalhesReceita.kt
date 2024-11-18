package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetalhesReceitaScreen(recipeId: Int?) {
    // Aqui você pode buscar os detalhes da receita usando o `recipeId` e exibir as informações
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Detalhes da Receita $recipeId", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Descrição da receita
        Text("Descrição: Aqui vai a descrição detalhada da receita", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredientes
        Text("Ingredientes:", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
        // Liste os ingredientes aqui

        Spacer(modifier = Modifier.height(16.dp))

        // Modo de preparo
        Text("Modo de Preparo:", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
        // Adicione o modo de preparo aqui

        Spacer(modifier = Modifier.height(16.dp))

        // Qualquer outro detalhe, como tempo de preparo, rendimento, etc.
    }
}
