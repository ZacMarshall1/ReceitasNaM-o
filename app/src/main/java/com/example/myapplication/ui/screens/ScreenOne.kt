package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.ScreenOneA
import com.example.myapplication.ui.screens.ScreenOneB
import com.example.myapplication.ui.screens.ScreenOneC
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar

object ScreenOne {
    val SCREEN_ONE_A_ROUTE = "t1a"
    val SCREEN_ONE_B_ROUTE = "t1b"
    val SCREEN_ONE_C_ROUTE = "t1c"
}

@Composable
fun ScreenOne(drawerState: DrawerState) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = ScreenOne.SCREEN_ONE_A_ROUTE
            ) {
                composable(ScreenOne.SCREEN_ONE_A_ROUTE) {
                    ScreenOneA(padding)
                }
                composable(ScreenOne.SCREEN_ONE_B_ROUTE) {
                    ScreenOneB(padding)
                }
                composable(ScreenOne.SCREEN_ONE_C_ROUTE) {
                    ScreenOneC(padding)
                }
            }
        },
        floatingActionButton = { FloatButton() },
        bottomBar = { BottomAppBarMinima(navController) }
    )
}

@Composable
private fun BottomAppBarMinima(navController: NavController) {

    NavigationBar(containerColor = Color(0xFF98D2FF)) {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_ONE_A_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Tela A") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_ONE_B_ROUTE)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "B",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Tela B") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(ScreenOne.SCREEN_ONE_C_ROUTE)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "C",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Tela C") }
        )
    }


//    BottomAppBar(
//        containerColor = Color(0xFF98D2FF)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            IconButton(
//                onClick = {
//                    navController.navigate(TelaUm.TELA_UM_A_ROUTE)
//                }) {
//                Icon(
//                    imageVector = Icons.Default.Call,
//                    contentDescription = "c",
//                    modifier = Modifier.size(40.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(40.dp))
//            IconButton(
//                onClick = {
//                    navController.navigate(TelaUm.TELA_UM_B_ROUTE)
//                }) {
//                Icon(
//                    imageVector = Icons.Default.Face,
//                    contentDescription = "f",
//                    modifier = Modifier.size(40.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(40.dp))
//            IconButton(
//                onClick = {
//                    navController.navigate(TelaUm.TELA_UM_C_ROUTE)
//                }) {
//                Icon(
//                    imageVector = Icons.Default.Build,
//                    contentDescription = "b",
//                    modifier = Modifier.size(40.dp)
//                )
//            }
//        }
//    }
}

@Composable
private fun FloatButton() {
    FloatingActionButton(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}