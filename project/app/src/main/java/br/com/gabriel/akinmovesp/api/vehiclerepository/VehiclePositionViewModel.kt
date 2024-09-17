package br.com.gabriel.akinmovesp.api.vehiclerepository

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.data.VehicleClusterItem
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehiclePositionViewModel @Inject constructor(
    application: Application,
    private val getPositionsVehicleUseCase: GetPositionsVehicleUseCase
) : AndroidViewModel(application) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    private val _userLocation = MutableStateFlow<Location?>(null)
    val userLocation: StateFlow<Location?> = _userLocation

    private val _vehicleClusterItems = MutableStateFlow<List<VehicleClusterItem>>(emptyList())
    val vehicleClusterItems: StateFlow<List<VehicleClusterItem>> = _vehicleClusterItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getLastKnownLocation()
        getVehicles()
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        _userLocation.value = location
                        _isLoading.value = false
                    }
                    .addOnFailureListener { exception ->
                        _errorMessage.value = exception.message
                        _isLoading.value = false
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun getVehicles() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            val result = getPositionsVehicleUseCase()
            result.onSuccess { positions ->
                val clusterItems = positions.l?.flatMap { linha ->
                    linha?.vs?.mapNotNull { veiculo ->
                        veiculo?.let {
                            VehicleClusterItem(
                                positionBus = LatLng(it.py ?: 0.0, it.px ?: 0.0),
                                titleBus = "Veículo ${it.p}",
                                snippetBus = "Linha ${linha.c}"
                            )
                        }
                    } ?: emptyList()
                }?.take(100) ?: emptyList() // Limite para 100 marcadores
                _vehicleClusterItems.value = clusterItems
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}