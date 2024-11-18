package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.screens.util.ScreenHomeBottomBar
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncluirEditarReceitasScreen(navController: NavController) {
    var titulo by remember { mutableStateOf(TextFieldValue("")) }
    var descricao by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Incluir Receita") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Adicionar ou Editar Receita", fontSize = 24.sp)


                TextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título da Receita") },
                    modifier = Modifier.fillMaxWidth()
                )


                TextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição da Receita") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Receita")
                }
            }
        }
    )
}