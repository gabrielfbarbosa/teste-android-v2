package br.com.gabriel.akinmovesp.request

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionRequest(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val locationPermissionState = rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(locationPermissionState.permission) {
        if (locationPermissionState.status.isGranted) {
            onPermissionGranted()
        }
    }

    when {
        locationPermissionState.status.isGranted -> {
            // Permissão já concedida, nada a fazer
        }
        locationPermissionState.status.shouldShowRationale -> {
            // Mostrar um diálogo explicando por que a permissão é necessária
            AlertDialog(
                onDismissRequest = {},
                title = { Text(text = "Permissão de Localização Necessária") },
                text = { Text(text = "Este aplicativo necessita da sua localização para centralizar o mapa na sua posição atual.") },
                confirmButton = {
                    TextButton(onClick = { locationPermissionState.launchPermissionRequest() }) {
                        Text("Conceder")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onPermissionDenied() }) {
                        Text("Negar")
                    }
                }
            )
        }
        !locationPermissionState.status.isGranted && !locationPermissionState.status.shouldShowRationale -> {
            // Solicitar a permissão
            SideEffect {
                locationPermissionState.launchPermissionRequest()
            }
        }
        else -> {
            // Permissão negada permanentemente
            AlertDialog(
                onDismissRequest = {},
                title = { Text(text = "Permissão de Localização Negada") },
                text = { Text(text = "Sem a permissão de localização, o aplicativo não pode centralizar o mapa na sua posição atual.") },
                confirmButton = {
                    TextButton(onClick = { onPermissionDenied() }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}