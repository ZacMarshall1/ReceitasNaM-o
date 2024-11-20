package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class Receita(val titulo: String, val descricao: String, val id: Int)

object ReceitaADMRotas {
    const val SCREEN_LIST_RECEPT_ROUTE = "listar_receitas"
    const val SCREEN_DETAILS_RECEPT_ROUTE = "detalhes_receitas"
}

@Composable
fun TelaReceitasADM(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {
    val receitas = listOf(
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
                        ScreenReceptListing(receitas, navCtrlReceitas)
                    }
                    composable("${ReceitaADMRotas.SCREEN_DETAILS_RECEPT_ROUTE}/{receitaId}") { backStackEntry ->
                        val receitaId = backStackEntry.arguments?.getString("receitaId")?.toInt() ?: 0
                        DetalhesRecomendadas(receitaId = receitaId, receitas = receitas)
                    }
                }
            }
        },
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav) }
    )
}



@Composable
private fun ScreenReceptListing(receitas: List<Receita>, navCtrl: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(receitas) { receita ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clickable {
                        navCtrl.navigate("${ReceitaADMRotas.SCREEN_DETAILS_RECEPT_ROUTE}/${receita.id}")
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.estrela),
                        contentDescription = "Ícone de estrela",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.TopStart)
                    )
                    Column(modifier = Modifier.padding(start = 40.dp)) {
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
}
