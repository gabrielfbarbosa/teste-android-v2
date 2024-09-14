package br.com.gabriel.akinmovesp.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.gabriel.akinmovesp.screens.ErrorScreen
import br.com.gabriel.akinmovesp.screens.LoadingScreen
import br.com.gabriel.akinmovesp.screens.MapScreen

sealed class Screen(val route: String) {
    object Loading : Screen("loading_screen")
    object Map : Screen("map_screen")
    object Error : Screen("error_screen")
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Loading.route) {
        composable(Screen.Loading.route) {
            LoadingScreen(navController = navController)
        }
        composable(Screen.Map.route) {
            MapScreen()
        }
        composable(Screen.Error.route) {
            ErrorScreen()
        }
    }
}