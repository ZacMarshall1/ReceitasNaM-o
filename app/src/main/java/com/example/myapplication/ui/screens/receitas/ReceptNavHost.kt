package com.example.myapplication.ui.screens.receitas

import android.content.Context
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.receitas.TelaReceitas

object ScreenOne {
    val SCREEN_HOME_HOST =  "Home"
    val SCREEN_MY_RECEPTS_HOST =  "Minhas Receitas"
    val SCREEN_RECOMMENDED_RECEPTS_HOST =  "Receitas Recomendadas"
}

@Composable
fun ReceitasNavHost(drawerState: DrawerState){

    val navCtrlBottomNav = rememberNavController()
    NavHost(navController = navCtrlBottomNav,
        startDestination = ScreenOne.SCREEN_HOME_HOST
    ){
        composable(ScreenOne.SCREEN_HOME_HOST){
            TelaHomeReceitas(drawerState, navCtrlBottomNav)
        }
        composable(ScreenOne.SCREEN_MY_RECEPTS_HOST ){
            TelaMinhasReceitas(drawerState, navCtrlBottomNav)
        }
        composable(ScreenOne.SCREEN_RECOMMENDED_RECEPTS_HOST){
            TelaReceitasADM(drawerState, navCtrlBottomNav)
        }
    }
}