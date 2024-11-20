package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesRecomendadas(receitaId: Int, receitas: List<Receita>) {
    val receita = receitas.find { it.id == receitaId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = receita?.titulo ?: "Detalhes da Receita") }
            )
        }
    ) { padding ->
        receita?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = it.titulo,
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.descricao,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            Text(
                text = "Receita não encontrada",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
