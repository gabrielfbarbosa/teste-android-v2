package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.gabriel.akinmovesp.api.estimatedrepository.EstimatedViewModel
import br.com.gabriel.akinmovesp.screens.components.CustomButton
import br.com.gabriel.akinmovesp.screens.components.CustomOutlinedTextField
import br.com.gabriel.akinmovesp.screens.components.EstimatedContent

@Composable
fun EstimatedScreen(viewModel: EstimatedViewModel = hiltViewModel()) {

    val estimated by viewModel.estimated.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    var stopCode by remember { mutableStateOf("") }

    var codeStopValid by remember { mutableStateOf<Int?>(null) }

    DisposableEffect(codeStopValid) {
        codeStopValid?.let { stopCode ->
            viewModel.startAutomaticRefresh(stopCode)
        }
        onDispose {
            viewModel.stopAutomaticRefresh()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Campo de Entrada para o Código da Parada
            CustomOutlinedTextField(
                value = stopCode,
                onValueChange = { stopCode = it },
                label = { Text("Código da Parada") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            CustomButton(
                onClick = {
                    val stop = stopCode.toIntOrNull()
                    if (stop != null) {
                        codeStopValid = stop
                        viewModel.getEstimated(stop)
                    } else {
                        viewModel.setErrorMessage("Código da parada inválido")
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp)
            ) {
                Text("Buscar")
            }

            if (estimated != null) {
                // Exibir as previsões
                EstimatedContent(previsao = estimated!!)
            } else if (errorMessage != null) {
                // Exibir mensagem de erro
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro: $errorMessage")
                }
            } else {
                // Estado inicial ou nenhum dado disponível
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Insira o código da parada para buscar a previsão.")
                }
            }
        }

        if (isLoading) {
            // Aplicar o efeito de embacamento
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)) // Escurece a tela levemente
                    .blur(16.dp) // Efeito de embacamento
                    .clickable(enabled = false) { } // Impedir interação com a tela
            ) {
                // Exibir o indicador de carregamento
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}