package br.com.gabriel.akinmovesp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.gabriel.akinmovesp.api.vehiclerepository.VehiclePositionViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun MapScreen(viewModel: VehiclePositionViewModel = hiltViewModel()) {

    val userLocation by viewModel.userLocation.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val clusterItems by viewModel.vehicleClusterItems.collectAsStateWithLifecycle()

    // Estado para controlar se o mapa deve ser exibido
    val shouldShowMap = !isLoading && errorMessage == null

    // Estado da posição da câmera, inicializado na localização do usuário quando disponível
    val cameraPositionState = rememberCameraPositionState {
        if (userLocation != null) {
            position = CameraPosition.fromLatLngZoom(
                LatLng(userLocation!!.latitude, userLocation!!.longitude),
                15f // Zoom mais próximo para a localização do usuário
            )
        } else {
            // Posição padrão caso a localização não esteja disponível
            position = CameraPosition.fromLatLngZoom(
                LatLng(-23.550520, -46.633308),
                10f
            )
        }
    }

    // Atualizar a posição da câmera quando a localização do usuário for obtida
    LaunchedEffect(userLocation) {
        userLocation?.let { location ->
            val userLatLng = LatLng(location.latitude, location.longitude)
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(userLatLng, 15f)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                // Indicador de Carregamento Centralizado
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                // Exibir Mensagem de Erro Centralizada
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ocorreu um erro:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage!!,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getVehicles() }) {
                        Text("Tentar Novamente")
                    }
                }
            }
            shouldShowMap -> {
                // Exibir o Mapa com os Marcadores
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(
                        isMyLocationEnabled = userLocation != null
                    ),
                    uiSettings = MapUiSettings(
                        myLocationButtonEnabled = true,
                        zoomControlsEnabled = true
                    )
                ) {
                    // Adicionar marcador para a localização do usuário, se disponível
                    userLocation?.let { location ->
                        Marker(
                            state = MarkerState(position = LatLng(location.latitude, location.longitude)),
                            title = "Você está aqui",
                            snippet = "Sua localização atual"
                        )
                    }

                    // Adicionar outros marcadores
                    clusterItems.forEach { item ->
                        val stateVehiculos =
                            rememberMarkerState(position = item.positionBus)
                        Marker(
                            state = stateVehiculos,
                            title = item.title,
                            snippet = item.snippet
                        )
                    }
                }
            }
            else -> {
                // Estado Inicial ou Outro Estado Não Tratado
                // Opcional: Você pode adicionar uma tela padrão ou deixar vazio
            }
        }
    }
}