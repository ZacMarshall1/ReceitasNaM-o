package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.receitas.ReceitasNavHost
import kotlinx.coroutines.launch

object ReceitasNaMaoRotas {
    val SCREEN_HOME_ROUTE = "tela_um"
    val SCREEN_MY_RECEPT_ROUTE = "tela_dois"
    val SCREEN_RECOMMENDED_RECEPT_ROUTE = "tela_tres" // Rota corrigida para "Receitas Recomendadas"
}

@Preview(
    device = Devices.PIXEL
)
@Composable
fun ReceitasNaMaoNavDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navCtrlDrawer = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navCtrlDrawer, drawerState)
        },
        content = {
            NavHost(
                navController = navCtrlDrawer,
                startDestination = ReceitasNaMaoRotas.SCREEN_HOME_ROUTE
            ) {
                composable(ReceitasNaMaoRotas.SCREEN_HOME_ROUTE) {
                    ReceitasNavHost(drawerState)
                }
                composable(ReceitasNaMaoRotas.SCREEN_MY_RECEPT_ROUTE) {
                    ReceitasNavHost(drawerState)
                }
                composable(ReceitasNaMaoRotas.SCREEN_RECOMMENDED_RECEPT_ROUTE) {
                    ReceitasNavHost(drawerState)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: ReceitasNaMaoRotas.SCREEN_HOME_ROUTE
    val ehRotaUm = rotaAtual == ReceitasNaMaoRotas.SCREEN_HOME_ROUTE
    val ehRotaDois = rotaAtual == ReceitasNaMaoRotas.SCREEN_MY_RECEPT_ROUTE
    val ehRotaTres = rotaAtual == ReceitasNaMaoRotas.SCREEN_RECOMMENDED_RECEPT_ROUTE

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.Red) // Alterado para uma cor mais suave
            .padding(30.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoapp),
            contentDescription = "Logo do ReceitasNaMao",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão de "Home"
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRotas.SCREEN_HOME_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),  // Garante que os ícones fiquem à esquerda
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(40.dp),
                    tint = getColorTexto(ehRotaUm)
                )
                Spacer(modifier = Modifier.width(10.dp))  // Espaçamento entre ícone e texto
                Text(
                    text = "Home",
                    fontSize = 30.sp,
                    color = getColorTexto(ehRotaUm)
                )
            }
        }

        // Botão de "Receitas Recomendadas"
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRotas.SCREEN_RECOMMENDED_RECEPT_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),  // Garante que os ícones fiquem à esquerda
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.estrela),
                    contentDescription = "Recomendadas",
                    modifier = Modifier.size(40.dp),
                    tint = getColorTexto(ehRotaTres)
                )
                Spacer(modifier = Modifier.width(8.dp))  // Espaço reduzido
                Text(
                    text = "Recomendadas",
                    fontSize = 30.sp,
                    color = getColorTexto(ehRotaTres)
                )
            }
        }

        // Botão de "Minhas Receitas"
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRotas.SCREEN_MY_RECEPT_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),  // Garante que os ícones fiquem à esquerda
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.receita),
                    contentDescription = "Receitas",
                    modifier = Modifier.size(40.dp),
                    tint = getColorTexto(ehRotaDois)
                )
                Spacer(modifier = Modifier.width(10.dp))  // Espaçamento entre ícone e texto
                Text(
                    text = "Receitas",
                    fontSize = 30.sp,
                    color = getColorTexto(ehRotaDois)
                )
            }
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color.Black
    } else {
        Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color.White
    } else {
        Color.White
    }
}
