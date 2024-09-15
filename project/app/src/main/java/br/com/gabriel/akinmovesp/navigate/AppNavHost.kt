package br.com.gabriel.akinmovesp.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.gabriel.akinmovesp.MainScreen
import br.com.gabriel.akinmovesp.screens.ErrorScreen
import br.com.gabriel.akinmovesp.screens.LinesScreen
import br.com.gabriel.akinmovesp.screens.LoadingScreen

sealed class Screen(val route: String) {
    object Loading : Screen("loading_screen")
    object Main : Screen("main_screen")
    object Lines : Screen("lines_screen")
    object Error : Screen("error_screen")
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Loading.route) {
        composable(Screen.Loading.route) {
            LoadingScreen(navController = navController)
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
        /*composable(Screen.Lines.route) {
            LinesScreen()
        }*/
        composable(Screen.Error.route) {
            ErrorScreen()
        }
    }
}