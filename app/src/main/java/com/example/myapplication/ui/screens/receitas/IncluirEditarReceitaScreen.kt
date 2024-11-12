package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.DataBase.MinhaReceita
import kotlinx.coroutines.launch

@Composable
fun IncluirEditarReceitaScreen(
    receitaId: Int? = null,
    viewModel: ReceitasViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    // Dados da nova receita
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf("") }
    var preparo by remember { mutableStateOf("") }

    var minhaReceita: MinhaReceita? by remember { mutableStateOf(null) }

    val label = if (receitaId == null) "Nova Receita" else "Editar Receita"

    LaunchedEffect(receitaId) {
        coroutineScope.launch {
            if (receitaId != null) {
                minhaReceita = viewModel.buscarReceitaPorId(receitaId)
                minhaReceita?.let {
                    titulo = it.titulo
                    descricao = it.descricao
                    ingredientes = it.ingredientes
                    preparo = it.preparo
                }
            }
        }
    }

    Column(
        modifier = Modifier.padding(
            top = 90.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = 30.dp
        )
    ) {
        Text(
            text = label,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = ingredientes,
            onValueChange = { ingredientes = it },
            label = { Text("Ingredientes") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = preparo,
            onValueChange = { preparo = it },
            label = { Text("Preparo") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutineScope.launch {
                val receitaSalvar = MinhaReceita(
                    id = receitaId,
                    titulo = titulo,
                    descricao = descricao,
                    ingredientes = ingredientes,
                    preparo = preparo
                )
                viewModel.gravar(receitaSalvar)
                navController.popBackStack()
            }
        }) {
            Text(text = "Salvar", fontSize = 30.sp)
        }
    }
}
