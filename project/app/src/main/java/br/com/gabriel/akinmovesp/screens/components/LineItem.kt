package br.com.gabriel.akinmovesp.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.gabriel.akinmovesp.api.models.linemodel.LineResponseModel

@Composable
fun LineItem(line: LineResponseModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Linha: ${line.lt}-${line.tl}", style = MaterialTheme.typography.titleMedium)
        Text(text = "Sentido 1: ${line.tp}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Sentido 2: ${line.ts}", style = MaterialTheme.typography.bodyMedium)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}