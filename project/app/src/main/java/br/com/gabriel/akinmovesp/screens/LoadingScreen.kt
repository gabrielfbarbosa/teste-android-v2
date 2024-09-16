package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.gabriel.akinmovesp.R
import br.com.gabriel.akinmovesp.api.authrepository.AuthViewModel
import br.com.gabriel.akinmovesp.navigate.Screen
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val authResult by viewModel.authResult.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.authenticate("4923d77245c50cf6587316acd575adce16573d38314f9b7dd9d7132119488f0c")
        //TODO: Token
    }

    LaunchedEffect(authResult) {
        when (authResult) {
            true -> {
                delay(8000)
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Loading.route) { inclusive = true }
                }
            }
            false -> {
                delay(8000)
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
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.aikomovesp), // Substitua com o ID da sua imagem
                contentDescription = "Imagem de carregamento",
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(12.dp)) // Tamanho da imagem
            )
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
    }
}