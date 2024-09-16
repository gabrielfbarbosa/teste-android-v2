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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.gabriel.akinmovesp.api.stoprepository.StopViewModel
import br.com.gabriel.akinmovesp.screens.components.CustomButton
import br.com.gabriel.akinmovesp.screens.components.CustomOutlinedTextField
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun StopsScreen(viewModel: StopViewModel = hiltViewModel()) {

    val paradas by viewModel.paradas.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    var searchQuery by remember { mutableStateOf("") }

    val cameraPositionState = rememberCameraPositionState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar

        CustomOutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar Parada") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        CustomButton(
            onClick = { viewModel.buscarParadas(searchQuery) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text("Buscar")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> {
                    // Loading Indicator
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                errorMessage != null -> {
                    // Error Message
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Erro: $errorMessage")
                    }
                }
                paradas != null -> {
                    // Map with Paradas
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                    ) {
                        paradas?.forEach { parada ->
                            val position = parada.py?.let { parada.px?.let { it1 ->
                                LatLng(it,it1)
                            } }
                            Marker(
                                state = MarkerState(position = position!!),
                                title = parada.np,
                                snippet = parada.ed
                            )
                        }

                        // Move camera to the first parada
                        if (!paradas.isNullOrEmpty()) {
                            LaunchedEffect(paradas) {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(
                                        LatLng(paradas!![0].py!!, paradas!![0].px!!),
                                        14f
                                    )
                                )
                            }
                        }
                    }
                }
                else -> {
                    // No data
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Nenhuma parada encontrada.")
                    }
                }
            }
        }
    }
}
