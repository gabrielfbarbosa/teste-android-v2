package br.com.gabriel.akinmovesp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.gabriel.akinmovesp.screens.LinesScreen
import br.com.gabriel.akinmovesp.screens.MapScreen
import br.com.gabriel.akinmovesp.navigate.bottomnavegate.BottomNavItem
import br.com.gabriel.akinmovesp.navigate.bottomnavegate.BottomNavigationBar
import br.com.gabriel.akinmovesp.screens.EstimatedScreen
import br.com.gabriel.akinmovesp.screens.StopsScreen
import br.com.gabriel.akinmovesp.ui.theme.AkinMoveSPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Aiko Move SP") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Map.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Map.route) {
                MapScreen()
            }
            composable(BottomNavItem.Lines.route) {
                LinesScreen()
            }
            composable(BottomNavItem.Stops.route) {
                StopsScreen()
            }
            composable(BottomNavItem.Estimated.route) {
                EstimatedScreen()
            }
        }
    }
}
