package com.example.myapplication.ui.screens.util

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.ui.screens.receitas.ScreenOne

@Composable
fun ScreenHomeBottomBar(navController: NavController){

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(containerColor = Color(0xFF1B1B1B)) {
        NavigationBarItem(
            selected = currentRoute == ScreenOne.SCREEN_HOME_HOST ,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_HOME_HOST)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home) ,
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Receita", color = Color.White) }
        )
        NavigationBarItem(
            selected = currentRoute == ScreenOne.SCREEN_MY_RECEPTS_HOST ,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_MY_RECEPTS_HOST)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.recept) ,
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Receitas Recomendadas", color = Color.White) }
        )
        NavigationBarItem(
            selected = currentRoute == ScreenOne.SCREEN_RECOMMENDED_RECEPTS_HOST ,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_RECOMMENDED_RECEPTS_HOST)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.favicon),
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Minhas Receitas", color = Color.White) }
        )
    }
}