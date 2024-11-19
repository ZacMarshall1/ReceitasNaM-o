package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.screens.DataBase.MinhaReceita
import com.example.myapplication.ui.screens.DataBase.ReceitasDatabase
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncluirEditarReceitasScreen(receitas: List<Receita>, navController: NavController) {
    var titulo by remember { mutableStateOf(TextFieldValue("")) }
    var descricao by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Função para salvar ou editar a receita
    fun salvarReceita() {
        val receita = MinhaReceita(
            titulo = titulo.text,
            descricao = descricao.text,
            ingredientes = "", // Aqui você pode adicionar o campo para os ingredientes
            preparo = "", // Aqui você pode adicionar o campo para o preparo
            tipoReceita = "NORMAL" // Ou definir outro tipo
        )

        scope.launch {
            val db = ReceitasDatabase.getInstance(context)
            db.receitaDao().gravarReceita(receita) // Salva ou atualiza no banco de dados
            navController.popBackStack() // Volta para a tela anterior
        }
    }

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
                        salvarReceita() // Chama a função para salvar no banco
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Receita")
                }
            }
        }
    )
}

