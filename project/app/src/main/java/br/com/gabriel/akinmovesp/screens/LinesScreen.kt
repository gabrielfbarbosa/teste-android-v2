package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import br.com.gabriel.akinmovesp.api.linerepository.LineViewModel
import br.com.gabriel.akinmovesp.screens.components.CustomButton
import br.com.gabriel.akinmovesp.screens.components.CustomOutlinedTextField
import br.com.gabriel.akinmovesp.screens.components.LineItem

@Composable
fun LinesScreen(viewModel: LineViewModel = hiltViewModel()) {
    val lines by viewModel.lines.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomOutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Código da Parada") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        CustomButton(
            onClick = { viewModel. getLines(searchQuery) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text("Buscar")
        }

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro: $errorMessage")
                }
            }
            lines != null -> {
                LazyColumn {
                    items(lines!!) { line ->
                        LineItem(line = line)
                    }
                }
            }
            else -> {
                // Estado inicial ou nenhum dado disponível
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Nenhuma linha encontrada.")
                }
            }
        }
    }
}