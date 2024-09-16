package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.gabriel.akinmovesp.api.estimatedrepository.EstimatedViewModel
import br.com.gabriel.akinmovesp.screens.components.EstimatedContent

@Composable
fun EstimatedScreen(viewModel: EstimatedViewModel = hiltViewModel()) {

    val previsao by viewModel.estimated.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    var stopCode by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Campo de Entrada para o Código da Parada
        OutlinedTextField(
            value = stopCode,
            onValueChange = { stopCode = it },
            label = { Text("Código da Parada") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            onClick = {
                val stop = stopCode.toIntOrNull()
                if (stop != null) {
                    viewModel.getEstimated(stop)
                } else {
                    // Exibir mensagem de erro se o código da parada não for válido
                    viewModel.setErrorMessage("Código da parada inválido")
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text("Buscar")
        }

        when {
            isLoading -> {
                // Exibir indicador de carregamento
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            errorMessage != null -> {
                // Exibir mensagem de erro
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro: $errorMessage")
                }
            }
            previsao != null -> {
                // Exibir as previsões
                EstimatedContent(previsao = previsao!!)
            }
            else -> {
                // Estado inicial ou nenhum dado disponível
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Insira o código da parada para buscar a previsão.")
                }
            }
        }
    }
}