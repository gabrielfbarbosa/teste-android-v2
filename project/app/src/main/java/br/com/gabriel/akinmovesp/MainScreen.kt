package br.com.gabriel.akinmovesp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.gabriel.akinmovesp.navigate.Screen
import br.com.gabriel.akinmovesp.screens.LinesScreen
import br.com.gabriel.akinmovesp.screens.MapScreen
import br.com.gabriel.akinmovesp.navigate.bottomnavegate.BottomNavItem
import br.com.gabriel.akinmovesp.navigate.bottomnavegate.BottomNavigationBar
import br.com.gabriel.akinmovesp.request.LocationPermissionRequest
import br.com.gabriel.akinmovesp.screens.EstimatedScreen
import br.com.gabriel.akinmovesp.screens.LoadingScreen
import br.com.gabriel.akinmovesp.screens.StopsScreen
import br.com.gabriel.akinmovesp.ui.theme.AkinMoveSPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var hasLocationPermission by remember { mutableStateOf(false) }
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
            if(hasLocationPermission){
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        if (!hasLocationPermission) {
            LocationPermissionRequest(
                onPermissionGranted = { hasLocationPermission = true },
                onPermissionDenied = {  /* Trate a negação conforme necessário */ }
            )
        } else {
            NavHost(
                navController = navController,
                startDestination = Screen.Loading.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Loading.route) {
                    LoadingScreen(navController = navController)
                }
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
}
