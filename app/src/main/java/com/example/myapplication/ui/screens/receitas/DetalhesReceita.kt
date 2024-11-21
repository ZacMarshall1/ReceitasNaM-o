package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetalhesReceitaScreen(
    navController: NavController,
    receita: Receita?, // Receita a ser detalhada
    excluirReceita: (Int) -> Unit // Função para excluir a receita
) {
    if (receita == null) {
        // Caso a receita seja nula
        Text(
            text = "Receita não encontrada",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), // Espaçamento externo aumentado
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detalhes da Receita",
            fontSize = 28.sp, // Fonte maior para o título
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 32.dp) // Espaçamento abaixo do título
        )

        Text(
            text = "Nome: ${receita.titulo}",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 24.dp) // Espaçamento abaixo do nome
        )

        Text(
            text = "Descrição: ${receita.descricao}",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 32.dp) // Espaçamento abaixo da descrição
        )

        // Botão de exclusão
        Button(
            onClick = {
                excluirReceita(receita.id) // Chama a função para excluir a receita pelo ID
                navController.popBackStack() // Retorna para a tela anterior
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.padding(top = 16.dp) // Espaçamento acima do botão
        ) {
            Text("Excluir Receita")
        }
    }
}