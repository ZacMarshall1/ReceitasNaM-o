package com.example.myapplication.ui.screens.receitas

import android.net.Uri
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncluirEditarReceitasScreen(receitas: MutableList<Receita>, navController: NavController) {
    var titulo by remember { mutableStateOf(TextFieldValue("")) }
    var descricao by remember { mutableStateOf(TextFieldValue("")) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
    }

    // Carrega a imagem sempre que imageUri mudar
    val context = LocalContext.current
    LaunchedEffect(imageUri) {
        imageUri?.let {
            bitmap = loadBitmapFromUri(context, it)
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

                // Botão para selecionar imagem
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red, // Fundo do botão vermelho
                        contentColor = Color.White // Cor do texto no botão
                    )
                ) {
                    Text("Selecionar Imagem")
                }

                // Pré-visualização da imagem selecionada
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Imagem Selecionada",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Button(
                    onClick = {
                        // Adiciona a receita à lista
                        receitas.add(
                            Receita(
                                titulo = titulo.text,
                                descricao = descricao.text,
                                id = receitas.size + 1
                            )
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red, // Fundo do botão vermelho
                        contentColor = Color.White // Cor do texto no botão
                    )
                ) {
                    Text("Salvar Receita")
                }
            }
        }
    )
}

// Função para carregar bitmap a partir de um URI
suspend fun loadBitmapFromUri(context: android.content.Context, uri: Uri): Bitmap? {
    return withContext(Dispatchers.IO) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(uri)
            .allowHardware(false)
            .build()
        val result = (loader.execute(request) as? SuccessResult)?.drawable
        result?.toBitmap()
    }
}
