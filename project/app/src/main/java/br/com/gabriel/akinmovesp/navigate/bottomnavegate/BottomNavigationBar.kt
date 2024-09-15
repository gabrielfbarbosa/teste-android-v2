package br.com.gabriel.akinmovesp.navigate.bottomnavegate

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Map,
        BottomNavItem.Lines,
        BottomNavItem.Stops
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
