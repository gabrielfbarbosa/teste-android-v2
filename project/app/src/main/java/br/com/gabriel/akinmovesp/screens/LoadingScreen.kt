package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.gabriel.akinmovesp.api.AuthViewModel
import br.com.gabriel.akinmovesp.navigate.Screen

@Composable
fun LoadingScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val authResult by viewModel.authResult.observeAsState()

    LaunchedEffect(Unit) {
//        viewModel.authenticate("SEU_TOKEN_AQUI")
        viewModel.authenticate("4923d77245c50cf6587316acd575adce16573d38314f9b7dd9d7132119488f0c")
    }

    LaunchedEffect(authResult) {
        when (authResult) {
            true -> {
                navController.navigate(Screen.Welcome.route) {
                    popUpTo(Screen.Loading.route) { inclusive = true }
                }
            }
            false -> {
                navController.navigate(Screen.Error.route) {
                    popUpTo(Screen.Loading.route) { inclusive = true }
                }
            }
            null -> {
                // Ainda carregando
            }
        }
    }

    // Conteúdo da tela
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}