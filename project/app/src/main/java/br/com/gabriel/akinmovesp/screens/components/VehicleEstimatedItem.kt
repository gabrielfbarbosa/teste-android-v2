package br.com.gabriel.akinmovesp.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.gabriel.akinmovesp.api.models.estimatedmodel.VehicleEstimated

@Composable
fun VehicleEstimatedItem(vehicleEstimated: VehicleEstimated) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Prefixo: ${vehicleEstimated.p}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Horário Previsto: ${vehicleEstimated.t}", style = MaterialTheme.typography.bodySmall)
        Text(
            text = if (vehicleEstimated.a == true) "Acessível: Sim" else "Acessível: Não",
            style = MaterialTheme.typography.bodySmall
        )
    }
}