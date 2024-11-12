package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

object ReceitaHomeRotas {
    val SCREEN_LIST_RECEPT_ROUTE = "listar receitas"
    val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir receitas"
}

@Composable
fun TelaHomeReceitas(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receita = mutableListOf(
        Receita(titulo = "1", descricao = "descrição da receita", id = 1),
        Receita(titulo = "2", descricao = "descrição da receita", id = 2),
        Receita(titulo = "3", descricao = "descrição da receita", id = 3),
        Receita(titulo = "4", descricao = "descrição da receita", id = 4),
        Receita(titulo = "5", descricao = "descrição da receita", id = 5)
    )

    val navCtrlReceitas = rememberNavController()

    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = { padding ->
            NavHost(
                navController = navCtrlReceitas,
                startDestination = ReceitaHomeRotas.SCREEN_LIST_RECEPT_ROUTE,
                modifier = Modifier.padding(top = 45.dp)
            ) {
                composable(ReceitaHomeRotas.SCREEN_LIST_RECEPT_ROUTE) {
                    ScreenReceptListing(receita)
                }
                composable(ReceitaHomeRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                    // Tela de inclusão de receitas
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "Tela de inclusão de Receitas")
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
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(receita) { receita ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = receita.titulo,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleMedium
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
private fun FloatButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            // Navega para a tela de inclusão de receitas
            navController.navigate(ReceitaHomeRotas.SCREEN_INCLUDE_RECEPT_ROUTE)
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}
