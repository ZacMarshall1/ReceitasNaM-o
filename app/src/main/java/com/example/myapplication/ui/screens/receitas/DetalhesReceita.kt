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
    receita: Receita?, // A receita pode ser nula
    editarReceita: (Int, String, String, String) -> Unit // Função para editar receita
) {
    // Verificação de nulidade para inicializar os estados
    val descricaoInicial = receita?.descricao ?: ""

    // Estados para os campos de edição
    var descricao by remember { mutableStateOf(descricaoInicial) }
    var isEditing by remember { mutableStateOf(false) } // Controle para exibir os campos de edição

    if (receita == null) {
        // Se a receita não for encontrada, exibe uma mensagem de erro
        Text(text = "Receita não encontrada")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título da tela
        Text(
            text = "Detalhes da Receita: ${receita.titulo}",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe os detalhes da receita
        if (!isEditing) {
            // Modo visualização
            Text(text = "Descrição: ${receita.descricao}")
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            // Modo edição
            TextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botão para editar ou salvar alterações
        if (isEditing) {
            Button(
                onClick = {
                    receita?.let {
                        // Atualiza a receita com as novas informações
                        isEditing = false // Desativa o modo de edição
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Salvar")
            }
        } else {
            // Botão para entrar no modo de edição
            Button(
                onClick = {
                    isEditing = true // Ativa o modo de edição
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Editar Receita")
            }
        }
    }
}
