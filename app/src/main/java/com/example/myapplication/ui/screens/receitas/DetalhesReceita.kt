package com.example.myapplication.ui.screens.receitas

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditarReceitaScreen(
    recipeId: Int?,
    image: Bitmap?,
    initialDescription: String = "",
    initialIngredientes: String = "",
    initialModoPreparo: String = "",
    onSave: (String, String, String) -> Unit, // Callback para salvar as alterações
    editarReceita: (Int, String, String, String) -> Unit // Callback para editar a receita no repositório
) {
    var descricao by remember { mutableStateOf(initialDescription) }
    var ingredientes by remember { mutableStateOf(initialIngredientes) }
    var modoPreparo by remember { mutableStateOf(initialModoPreparo) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Editar Receita $recipeId",
            fontSize = 24.sp,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Exibição ou edição da imagem
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

        // Campo para editar a descrição
        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para editar ingredientes
        TextField(
            value = ingredientes,
            onValueChange = { ingredientes = it },
            label = { Text("Ingredientes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para editar modo de preparo
        TextField(
            value = modoPreparo,
            onValueChange = { modoPreparo = it },
            label = { Text("Modo de Preparo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão para salvar alterações
        Button(
            onClick = {
                recipeId?.let { id ->
                    // Chamada da função editarReceita
                    editarReceita(id, descricao, ingredientes, modoPreparo)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Salvar")
        }
    }
}
