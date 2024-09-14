package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.gabriel.akinmovesp.api.vehicle.VeiculosViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun MapScreen(viewModel: VeiculosViewModel = hiltViewModel()) {

    val posicoesVeiculos by viewModel.posicoesVeiculos.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(true)
    val errorMessage by viewModel.errorMessage.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarPosicoesVeiculos()
    }

    if (isLoading) {
        // Exibir mensagem de boas-vindas enquanto carrega
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "BEM-VINDO AO AIKO MOVE SÃO PAULO")
        }
    } else if (errorMessage != null) {
        // Exibir mensagem de erro
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Erro: $errorMessage")
        }
    } else if (posicoesVeiculos != null) {
        // Exibir o mapa com as posições dos veículos
        val cameraPositionState = rememberCameraPositionState {
            // Defina uma posição inicial
            position = CameraPosition.fromLatLngZoom(LatLng(-23.550520, -46.633308), 10f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Adicionar marcadores para cada veículo
            posicoesVeiculos?.l?.forEach { linha ->
                linha.vs.forEach { veiculo ->

                    val stateVehiculos = rememberMarkerState(position = LatLng(veiculo.px, veiculo.py))

                    Marker(
                        state = stateVehiculos,
                        title = "Veículo ${veiculo.p}",
                        snippet = "Linha ${linha.c}"
                    )
                }
            }
        }
    } else {
        // Estado inicial ou nenhum dado disponível
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Nenhum dado disponível.")
        }
    }
}