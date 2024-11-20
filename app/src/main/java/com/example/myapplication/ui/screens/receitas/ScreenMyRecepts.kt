package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

object ReceitaRotas {
    const val SCREEN_LIST_RECEPT_ROUTE = "listar receitas"
    const val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir receitas"
    const val SCREEN_RECOMMENDED_ROUTE = "screen_recommended"
    const val SCREEN_RECIPE_DETAIL_ROUTE = "detalhes receita"
}

@Composable
fun TelaMinhasReceitas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receitas = remember {
        mutableStateListOf(
            Receita("Receita 1", "Descrição da Receita 1", 1),
            Receita("Receita 2", "Descrição da Receita 2", 2),
            Receita("Receita 3", "Descrição da Receita 3", 3)
        )
    }

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
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    composable(ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE) {
                        ScreenReceptListing(receitas, navCtrlReceitas)
                    }
                    composable(ReceitaRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                        IncluirEditarReceitasScreen(receitas, navCtrlReceitas)
                    }
                    composable(ReceitaRotas.SCREEN_RECOMMENDED_ROUTE) {
                        ScreenRecommended()
                    }
                    composable("${ReceitaRotas.SCREEN_RECIPE_DETAIL_ROUTE}/{recipeId}") { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getString("recipeId")?.toIntOrNull()
                        val receitaDetalhe = receitas.find { it.id == recipeId }
                        DetalhesReceitaScreen(receitaDetalhe)
                    }
                }
            }
        },
        floatingActionButton = { FloatButton(navCtrlReceitas) },
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun ScreenReceptListing(receitas: List<Receita>, navController: NavController) {
    val rows = (receitas.size + 2) / 3 // Divide a lista em grupos de 3
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 0 until rows) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                for (j in 0 until 3) {
                    val index = i * 3 + j
                    if (index < receitas.size) {
                        val currentReceita = receitas[index]
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
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
                    } else {
                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun DetalhesReceitaScreen(receita: Receita?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        receita?.let {
            Text(text = "Detalhes da Receita: ${it.titulo}", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Descrição: ${it.descricao}", fontSize = 18.sp)
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
        Icon(imageVector = Icons.Default.Add, contentDescription = "+")
    }
}
