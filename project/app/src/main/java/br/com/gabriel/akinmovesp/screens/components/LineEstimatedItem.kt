package br.com.gabriel.akinmovesp.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.gabriel.akinmovesp.api.models.estimatedmodel.LineEstimated

@Composable
fun LineEstimatedItem(lineEstimated: LineEstimated) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Linha: ${lineEstimated.c}", style = MaterialTheme.typography.titleSmall)
        Text(text = "Sentido: ${lineEstimated.lt0} - ${lineEstimated.lt1}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Veículos previstos:", style = MaterialTheme.typography.bodyMedium)
        lineEstimated.vs?.forEach { veiculo ->
            veiculo?.let {
                VehicleEstimatedItem(vehicleEstimated = it)
            }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}