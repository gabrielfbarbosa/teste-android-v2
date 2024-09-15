package br.com.gabriel.akinmovesp.api.vehiclerepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.vehiclemodel.PositionResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val getPosicoesVehicleUseCase: GetPosicoesVehicleUseCase
) : ViewModel() {

    private val _posicoesVeiculos = MutableLiveData<PositionResponseModel?>()
    val posicoesVeiculos: LiveData<PositionResponseModel?> = _posicoesVeiculos

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun carregarPosicoesVeiculos() {
        viewModelScope.launch {
            val result = getPosicoesVehicleUseCase()
            result.onSuccess { posicoes ->
                _posicoesVeiculos.value = posicoes
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}