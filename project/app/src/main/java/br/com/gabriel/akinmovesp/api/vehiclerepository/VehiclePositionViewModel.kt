package br.com.gabriel.akinmovesp.api.vehiclerepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.vehiclemodel.PositionResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehiclePositionViewModel @Inject constructor(
    private val getPosicoesVehicleUseCase: GetPositionsVehicleUseCase
) : ViewModel() {

    private val _positionsVehicles = MutableLiveData<PositionResponseModel?>()
    val vehiclesPositions: LiveData<PositionResponseModel?> = _positionsVehicles

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private var refreshJob: Job? = null
    private val refreshIntervalMillis: Long = 5000 // 5 segundos

    init {
        startAutomaticRefresh()
    }

    private fun startAutomaticRefresh() {
        // Evita múltiplas chamadas
        if (refreshJob?.isActive == true) return

        refreshJob = viewModelScope.launch {
            while (isActive) {
                getPositionsVehicles()
                delay(refreshIntervalMillis)
            }
        }
    }

    internal fun stopAutomaticRefresh() {
        refreshJob?.cancel()
        refreshJob = null
    }

    fun getPositionsVehicles() {
        viewModelScope.launch {
            val result = getPosicoesVehicleUseCase()
            result.onSuccess { positions ->
                _positionsVehicles.value = positions
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopAutomaticRefresh()
    }
}