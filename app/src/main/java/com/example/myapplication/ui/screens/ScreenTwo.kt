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
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.screens.util.ReceitasNaMaoTopBar

@Composable
fun ScreenTwo(drawerState: DrawerState) {
    Scaffold(
        topBar = { ReceitasNaMaoTopBar(drawerState) },
        content = { padding -> ScreenOneA(padding) },
        floatingActionButton = { FloatButton() },
        bottomBar = { BottomAppBarMinima() }
    )
}

@Composable
private fun BottomAppBarMinima() {
    BottomAppBar(
        containerColor = Color(0xFF98D2FF)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "c",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(40.dp))
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "f",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(40.dp))
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "b",
                modifier = Modifier.size(40.dp)
            )
        }

    }
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

@Composable
private fun ScreenTwo(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tela 2",
            Modifier.padding(padding),
            fontSize = 40.sp
        )
    }
}

