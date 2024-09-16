package br.com.gabriel.akinmovesp.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.gabriel.akinmovesp.api.models.estimatedmodel.EstimatedResponseModel

@Composable
fun EstimatedContent(previsao: EstimatedResponseModel) {
    if (previsao.p != null && !previsao.p.l.isNullOrEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Parada: ${previsao.p.np}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(previsao.p.l!!) { linhaPrevisao ->
                linhaPrevisao?.let {
                    LineEstimatedItem(lineEstimated = it)
                }
            }
        }
    } else {
        // Exibir mensagem informando que não há previsão para esta parada
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Não há previsão para esta parada.")
        }
    }
}



