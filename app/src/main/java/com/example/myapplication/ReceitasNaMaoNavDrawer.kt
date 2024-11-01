package com.example.trainup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.myapplication.ui.screens.receitas.ReceitasNavHost
import kotlinx.coroutines.launch

object ReceitasNaMaoRoutes {
    val SCREEN_RECOMMENDED_ROUTE = "tela_um"
    val SCREEN_MY_RECIPES_ROUTE = "tela_dois"
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
                startDestination = ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE
            ) {
                composable(ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE) {
                    ReceitaNavHost(drawerState)
                }
                composable(ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE) {
                    ScreenMyRecipes(drawerState)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE
    val ehRotaUm = rotaAtual == ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE
    val ehRotaDois = rotaAtual == ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color(0xFFFF0000))
            .padding(30.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = res.drawable.logoapp),
            contentDescription = "Logo Receitas Na Mao",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão de treino
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                painterResource(id = R.drawable.academia),
                contentDescription = "Receita Recomendada",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaUm)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Receita",
                fontSize = 30.sp,
                color = getColorTexto(ehRotaUm)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botão de minhas receitas
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                painterResource(id = R.drawable.config),
                contentDescription = "Minhas Receitas",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaDois)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Minhas Receitas",
                fontSize = 25.sp,
                color = getColorTexto(ehRotaDois)
            )
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color(0xFF182E6F)
    } else {
        Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    return if (estaSelecionada) {
        Color.Yellow
    } else {
        Color(0xFFB0BEC5)
    }
}
