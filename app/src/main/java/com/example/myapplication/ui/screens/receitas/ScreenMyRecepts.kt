package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar
import com.example.myapplication.ui.screens.util.ScreenHomeBottomBar
import com.example.myapplication.ui.screens.receitas.IncluirEditarReceitasScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

object ReceitaRotas {
    val SCREEN_LIST_RECEPT_ROUTE = "listar receitas"
    val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir receitas"
    val SCREEN_RECOMMENDED_ROUTE = "screen_recommended"
    val SCREEN_RECIPE_DETAIL_ROUTE = "detalhes receita" // Rota para a nova tela de detalhes
}

@Composable
fun TelaMinhasReceitas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receita = mutableListOf(
        Receita(titulo = "Receita 1", descricao = "descrição da receita", id = 1),
        Receita(titulo = "Receita 2", descricao = "descrição da receita", id = 2),
        Receita(titulo = "Receita 3", descricao = "descrição da receita", id = 3),
        Receita(titulo = "Receita 4", descricao = "descrição da receita", id = 4),
        Receita(titulo = "Receita 5", descricao = "descrição da receita", id = 5),
        Receita(titulo = "Receita 6", descricao = "descrição da receita", id = 6),
        Receita(titulo = "Receita 7", descricao = "descrição da receita", id = 7),
        Receita(titulo = "Receita 8", descricao = "descrição da receita", id = 8),
        Receita(titulo = "Receita 9", descricao = "descrição da receita", id = 9),
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
                        text = "Minhas Receitas",
                        fontSize = 24.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.receita),
                        contentDescription = "Minhas Receitas",
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 8.dp)
                    )
                }

                NavHost(
                    navController = navCtrlReceitas,
                    startDestination = ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE,
                    modifier = Modifier.padding(top = 45.dp)
                ) {
                    composable(ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE) {
                        ScreenReceptListing(receita = receita, navController = navCtrlReceitas)
                    }
                    composable(ReceitaRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                        IncluirEditarReceitasScreen(receitas = receita, navController = navCtrlReceitas) // Passando apenas o navController
                    }
                    composable(ReceitaRotas.SCREEN_RECOMMENDED_ROUTE) {
                        ScreenRecommended()
                    }
                    composable("${ReceitaRotas.SCREEN_RECIPE_DETAIL_ROUTE}/{recipeId}") { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getString("recipeId")?.toInt()
                        DetalhesReceitaScreen(recipeId)
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navCtrlReceitas) },
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}


@Composable
private fun ScreenReceptListing(receita: MutableList<Receita>, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 4 linhas com 3 cartões por linha
        for (i in 0 until 3) { // 4 linhas
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // 3 cartões por linha
                for (j in 0..2) { // 3 colunas
                    val index = i * 3 + j
                    if (index < receita.size) {
                        val currentReceita = receita[index]
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f) // Mantém os cards quadrados
                                .clickable {
                                    navController.navigate("${ReceitaRotas.SCREEN_RECIPE_DETAIL_ROUTE}/${currentReceita.id}")
                                },
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.receita),
                                        contentDescription = "Ícone de Receita",
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = currentReceita.titulo,
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenRecommended() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tela de Recomendações", fontSize = 24.sp)
    }
}

@Composable
private fun FloatButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(ReceitaRotas.SCREEN_INCLUDE_RECEPT_ROUTE)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}
