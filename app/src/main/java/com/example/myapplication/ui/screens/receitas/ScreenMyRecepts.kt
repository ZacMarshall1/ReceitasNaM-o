package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar
import com.example.myapplication.ui.screens.util.ScreenHomeBottomBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add


object ReceitaRotas {
    val SCREEN_LIST_RECEPT_ROUTE = "listar receitas"
    val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir receitas"
    val SCREEN_RECOMMENDED_ROUTE = "screen_recommended" // Adicionando a nova rota
}

@Composable
fun TelaMinhasReceitas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receita = mutableListOf(
        Receita(titulo = "Segunda-Feira", descricao = "descrição da receita", id = 1),
        Receita(titulo = "Terça-Feira", descricao = "descrição da receita", id = 2),
        Receita(titulo = "Quarta-Feira", descricao = "descrição da receita", id = 3),
        Receita(titulo = "Quinta-Feira", descricao = "descrição da receita", id = 4),
        Receita(titulo = "Sexta-Feira", descricao = "descrição da receita", id = 5)
    )

    val navCtrlReceitas = rememberNavController()

    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Minhas Receitas",
                        fontSize = 24.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.myrecipe),
                        contentDescription = "Minhas Receitas",
                        modifier = Modifier.size(45.dp).padding(start = 8.dp)
                    )
                }

                NavHost(
                    navController = navCtrlReceitas,
                    startDestination = ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE,
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    composable(ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE) {
                        ScreenReceptListing(receita)
                    }
                    composable(ReceitaRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(modifier = Modifier.height(200.dp))
                            Text(text = "Tela de inclusão de Receitas")
                        }
                    }
                    composable(ReceitaRotas.SCREEN_RECOMMENDED_ROUTE) {
                        ScreenRecommended() // Tela recomendada sendo chamada aqui
                    }
                }
            }
        },
        floatingActionButton = { FloatButton() },
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun ScreenReceptListing(receita: MutableList<Receita>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(receita) { receita ->
            Card(
                modifier = Modifier
                    .padding(16.dp) // Espaço entre os cards
                    .fillMaxWidth(), // Para que o card preencha a largura disponível
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Sombra do ca
            ) {
                // Conteúdo do Card
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = receita.titulo,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleMedium // Estilo de texto do Material
                    )
                    Text(
                        text = receita.descricao,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenRecommended() {
    // Tela de Recomendações
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tela de Recomendações", fontSize = 24.sp)
    }
}

@Composable
private fun FloatButton() {
    FloatingActionButton(onClick = { }) {
        var icon = @Composable {
            Icon(
                painter = painterResource(id = R.drawable.icons),
                contentDescription = "A",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

data class Receita(
    var titulo: String,
    var descricao: String,
    var concluido: Boolean = false,
    var id: Int? = null
)

