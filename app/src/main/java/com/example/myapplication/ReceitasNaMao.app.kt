package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.ScreenOne
import com.example.myapplication.ui.screens.ScreenThree
import com.example.myapplication.ui.screens.ScreenTwo
import kotlinx.coroutines.launch

object ReceitasNaMaoRoutes {
    val SCREEN_ONE_ROUTE = "Home"
    val SCREEN_TWO_ROUTE = "Minhas Receitas"
    val SCREEN_THREE_ROUTE = "Receitas Recomendadas"
}

@Composable
fun ReceitasNaMaoApp(){

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = ReceitasNaMaoRoutes.SCREEN_ONE_ROUTE)
            {
                composable(ReceitasNaMaoRoutes.SCREEN_ONE_ROUTE){
                    ScreenOne(drawerState)
                }
                composable(ReceitasNaMaoRoutes.SCREEN_TWO_ROUTE){
                    ScreenTwo(drawerState)
                }
                composable(ReceitasNaMaoRoutes.SCREEN_THREE_ROUTE){
                    ScreenThree(drawerState)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: ReceitasNaMaoRoutes.SCREEN_ONE_ROUTE

    val ehRotaUm = rotaAtual == ReceitasNaMaoRoutes.SCREEN_ONE_ROUTE
    val ehRotaDois = rotaAtual == ReceitasNaMaoRoutes.SCREEN_TWO_ROUTE
    val ehRotaTres = rotaAtual == ReceitasNaMaoRoutes.SCREEN_THREE_ROUTE

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_ONE_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                //imageVector = Icons.Default.Call,
                painter = painterResource(id = R.drawable.receita),
                contentDescription = "c",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaUm)
            )
            Text(text = "Home", fontSize = 25.sp,
                color = getColorTexto(ehRotaUm))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_TWO_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                //imageVector = Icons.Default.Call,
                painter = painterResource(id = R.drawable.receita),
                contentDescription = "c",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaDois)
            )
            Text(text = "Minhas Receitas", fontSize = 25.sp,
                color = getColorTexto(ehRotaDois))
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            ),
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_THREE_ROUTE)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                //imageVector = Icons.Default.Call,
                painter = painterResource(id = R.drawable.star),
                contentDescription = "c",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaTres)
            )
            Text(text = "Receitas Recomendadas", fontSize = 25.sp,
                color = getColorTexto(ehRotaTres))
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Magenta
    } else {
        return Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Black
    } else {
        return Color.DarkGray
    }
}
