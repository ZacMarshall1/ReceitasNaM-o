package com.example.myapplication.ui.screens.receitas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
){
    var receita = mutableListOf(

        Receita(
            titulo = "1",
            descricao = "descrição da receita",
            id = 1
        ),
        Receita(
            titulo = "2",
            descricao = "descrição da receita",
            id = 2
        ),
        Receita(
            titulo = "3",
            descricao = "descrição da receita",
            id = 3
        ),
        Receita(
            titulo = "4",
            descricao = "descrição da receita",
            id = 3
        ),
        Receita(
            titulo = "5",
            descricao = "descrição da receita",
            id = 3
        ),

        )

    val navCtrlReceitas = rememberNavController()

    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = {padding ->

            NavHost(
                navController = navCtrlReceitas,
                startDestination = ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE,
                modifier = Modifier.padding(top = 45.dp)
            )
            {
                composable(ReceitaRotas.SCREEN_LIST_RECEPT_ROUTE){
                    ScreenReceptListing(receita)
                }
                composable(ReceitaRotas.SCREEN_INCLUDE_RECEPT_ROUTE){
                    Column (modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "Tela de inclusão de Receitas")
                    }

                }
            }
        },
        floatingActionButton = {FloatButton()},
        bottomBar = { ScreenHomeBottomBar(navCtrlBottomNav)}
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
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Sombra do card
                shape = RoundedCornerShape(8.dp) // Cantos arredondados
            ) {
                // Conteúdo do Card
                Column(
                    modifier = Modifier.padding(16.dp) // Espaço interno do card
                ) {
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

data class ReceitaHome(
    var titulo: String,
    var descricao: String,
    var concluido: Boolean = false,
    var id: Int? = null
)

@Composable
private fun FloatButton(){
    FloatingActionButton(onClick = {  }) {
        Icon(
            imageVector = Icons.Default.Add ,
            contentDescription = "+" )

    }
}

