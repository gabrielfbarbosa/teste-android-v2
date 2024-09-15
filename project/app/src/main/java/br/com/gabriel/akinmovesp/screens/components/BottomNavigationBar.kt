package br.com.gabriel.akinmovesp.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Map : BottomNavItem("map", "Mapa", Icons.Default.Place)
    object Lines : BottomNavItem("lines", "Linhas", Icons.Default.ArrowForward)
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Map,
        BottomNavItem.Lines
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Evita recriar o back stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Evita múltiplas cópias do mesmo destino
                        launchSingleTop = true
                        // Restaura o estado previamente salvo
                        restoreState = true
                    }
                }
            )
        }
    }
}
