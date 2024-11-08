package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.DataBase.MinhaReceita
import com.example.myapplication.ui.screens.DataBase.ReceitasDatabase
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
    var ingredientes by remember { mutableStateOf("") }  // Novo campo
    var preparo by remember { mutableStateOf("") }        // Novo campo

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
