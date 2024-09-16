package br.com.gabriel.akinmovesp.navigate.bottomnavegate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Map : BottomNavItem("map", "Mapa", Icons.Default.Place)
    object Lines : BottomNavItem("lines", "Linhas", Icons.Default.ArrowForward)
    object Stops : BottomNavItem("paradas", "Paradas", Icons.Default.Home)
    object Estimated : BottomNavItem("previsao", "Previsão", Icons.Default.Info)
}