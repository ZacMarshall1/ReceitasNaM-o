package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import kotlinx.coroutines.launch

object ReceitasNaMaoRoutes {
    const val SCREEN_RECOMMENDED_ROUTE = "tela_um"
    const val SCREEN_MY_RECIPES_ROUTE = "tela_dois"
}

@Preview(device = Devices.PIXEL)
@Composable
fun ReceitasNaMaoNavDrawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController, drawerState) },
        content = {
            NavHost(
                navController = navController,
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
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route ?: ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE

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
            painter = painterResource(id = R.drawable.logoapp),
            contentDescription = "Logo Receitas Na Mão",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão de Receita Recomendada
        DrawerButton(
            isSelected = currentRoute == ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE,
            iconResId = R.drawable.bandeja,
            text = "Receita",
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE) {
                    popUpTo(ReceitasNaMaoRoutes.SCREEN_RECOMMENDED_ROUTE) { inclusive = true }
                    launchSingleTop = true
                }
                coroutineScope.launch { drawerState.close() }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botão de Minhas Receitas
        DrawerButton(
            isSelected = currentRoute == ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE,
            iconResId = R.drawable.config,
            text = "Minhas Receitas",
            onClick = {
                navController.navigate(ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE) {
                    popUpTo(ReceitasNaMaoRoutes.SCREEN_MY_RECIPES_ROUTE) { inclusive = true }
                    launchSingleTop = true
                }
                coroutineScope.launch { drawerState.close() }
            }
        )
    }
}

@Composable
private fun DrawerButton(
    isSelected: Boolean,
    iconResId: Int,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        colors = ButtonDefaults.buttonColors(containerColor = getColorMenu(isSelected)),
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = text,
            modifier = Modifier.size(40.dp),
            tint = getColorTexto(isSelected)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 25.sp,
            color = getColorTexto(isSelected)
        )
    }
}

fun getColorMenu(isSelected: Boolean): Color {
    return if (isSelected) Color(0xFF182E6F) else Color.Transparent
}

fun getColorTexto(isSelected: Boolean): Color {
    return if (isSelected) Color.Yellow else Color(0xFFB0BEC5)
}

@Composable
fun ReceitaNavHost(drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Conteúdo da tela Receita Recomendada")
    }
}

@Composable
fun ScreenMyRecipes(drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Conteúdo da tela Minhas Receitas")
    }
}
