package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar
import com.example.myapplication.ui.screens.util.ScreenHomeBottomBar

object ReceitaADMRotas {
    val SCREEN_LIST_RECEPT_ROUTE = "listar receitas"
    val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir receitas"
}

@Composable
fun TelaReceitasADM(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receita = mutableListOf(
        Receita(titulo = "1", descricao = "descrição da receita", id = 1),
        Receita(titulo = "2", descricao = "descrição da receita", id = 2),
        Receita(titulo = "3", descricao = "descrição da receita", id = 3),
        Receita(titulo = "4", descricao = "descrição da receita", id = 4),
        Receita(titulo = "5", descricao = "descrição da receita", id = 5),
        Receita(titulo = "6", descricao = "descrição da receita", id = 6),
        Receita(titulo = "7", descricao = "descrição da receita", id = 7),
        Receita(titulo = "8", descricao = "descrição da receita", id = 8),
        Receita(titulo = "9", descricao = "descrição da receita", id = 9)
    )

    val navCtrlReceitas = rememberNavController()

    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Receitas Recomendadas",
                        fontSize = 24.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.estrela),
                        contentDescription = "fav icon",
                        modifier = Modifier.size(45.dp).padding(start = 8.dp)
                    )
                }

                NavHost(
                    navController = navCtrlReceitas,
                    startDestination = ReceitaADMRotas.SCREEN_LIST_RECEPT_ROUTE,
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    composable(ReceitaADMRotas.SCREEN_LIST_RECEPT_ROUTE) {
                        ScreenReceptListing(receita)
                    }
                    composable(ReceitaADMRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                        // Implementar a tela de inclusão de receitas aqui
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Tela de inclusão de receitas
                        }
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navCtrlReceitas) },
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun ScreenReceptListing(receita: MutableList<Receita>) {
    // Exibe as receitas em um grid de 3x3
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(receita) { receita ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Para garantir que os cards sejam quadrados
                    .clickable {
                        // Navegar para detalhes da receita
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Imagem ou ícone para destacar
                    Image(
                        painter = painterResource(id = R.drawable.estrela), // Estrela ou ícone de destaque
                        contentDescription = "Ícone de recomendação",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = receita.titulo,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(ReceitaADMRotas.SCREEN_INCLUDE_RECEPT_ROUTE)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}

data class Receita(
    var titulo: String,
    var descricao: String,
    var id: Int? = null
)

@Composable
fun DetalhesReceitaScreen(recipeId: Int?) {
    // Detalhes da receita
    Text("Detalhes da receita $recipeId")
}