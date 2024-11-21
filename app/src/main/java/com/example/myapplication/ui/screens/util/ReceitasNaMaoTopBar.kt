package com.example.myapplication.ui.screens.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceitasNaMaoTopBar(drawerState: DrawerState) {

    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start =50.dp), // Adiciona padding para deslocar o logo mais à direita
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(48.dp)) // Compensação para o ícone de menu
                Image(
                    painter = painterResource(id = R.drawable.logoapp),
                    contentDescription = "Logo do App",
                    modifier = Modifier.size(140.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Red)
    )
}
