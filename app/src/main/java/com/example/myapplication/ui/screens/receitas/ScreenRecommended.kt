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

// Classe de dados para representar as receitas
data class Receita(val titulo: String, val descricao: String, val id: Int)

object ReceitaADMRotas {
    const val SCREEN_LIST_RECEPT_ROUTE = "listar_receitas"
    const val SCREEN_INCLUDE_RECEPT_ROUTE = "incluir_receitas"
}

@Composable
fun TelaReceitasADM(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receitas = mutableListOf(
        Receita(titulo = "Receita 1", descricao = "Descrição da receita 1", id = 1),
        Receita(titulo = "Receita 2", descricao = "Descrição da receita 2", id = 2),
        Receita(titulo = "Receita 3", descricao = "Descrição da receita 3", id = 3),
        Receita(titulo = "Receita 4", descricao = "Descrição da receita 4", id = 4),
        Receita(titulo = "Receita 5", descricao = "Descrição da receita 5", id = 5)
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
                        contentDescription = "Ícone de estrela",
                        modifier = Modifier
                            .size(45.dp)
                            .padding(start = 8.dp)
                    )
                }

                NavHost(
                    navController = navCtrlReceitas,
                    startDestination = ReceitaADMRotas.SCREEN_LIST_RECEPT_ROUTE,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    composable(ReceitaADMRotas.SCREEN_LIST_RECEPT_ROUTE) {
                        ScreenReceptListing(receitas)
                    }
                    composable(ReceitaADMRotas.SCREEN_INCLUDE_RECEPT_ROUTE) {
                        IncluirEditarReceitasScreen(receitas, navCtrlReceitas)
                    }
                }
            }
        },
        // Não renderizando mais o botão de FAB nas receitas recomendadas
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun ScreenReceptListing(receitas: List<Receita>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(receitas) { receita ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
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
                    Spacer(modifier = Modifier.height(8.dp))
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
