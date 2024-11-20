package com.example.myapplication.ui.screens.receitas

import android.content.Context
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


object ScreenOne {
    val SCREEN_HOME_HOST =  "Home"
    val SCREEN_MY_RECEPTS_HOST =  "Minhas Receitas"
    val SCREEN_RECOMMENDED_RECEPTS_HOST =  "Receitas Recomendadas"
}

@Composable
fun ReceitasNavHost(drawerState: DrawerState){

    val navCtrlBottomNav = rememberNavController()
    NavHost(
        navController = navCtrlBottomNav,
        startDestination = ReceitaHomeRotas.SCREEN_HOME_HOST
    ) {
        composable(ReceitaHomeRotas.SCREEN_HOME_HOST) {
            TelaHomeReceitas(drawerState, navCtrlBottomNav)
        }
        composable(ReceitaHomeRotas.SCREEN_LIST_RECEPT_ROUTE) {
            TelaReceitasADM(drawerState, navCtrlBottomNav)
        }
        composable(ReceitaHomeRotas.SCREEN_MY_RECIPES_ROUTE) {
            TelaMinhasReceitas(drawerState, navCtrlBottomNav)
        }
    }
}