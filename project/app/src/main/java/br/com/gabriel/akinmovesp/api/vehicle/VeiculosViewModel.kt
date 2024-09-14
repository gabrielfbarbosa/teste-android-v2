package br.com.gabriel.akinmovesp.api.vehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.PosicaoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeiculosViewModel @Inject constructor(
    private val getPosicoesVeiculosUseCase: GetPosicoesVeiculosUseCase
) : ViewModel() {

    private val _posicoesVeiculos = MutableLiveData<PosicaoResponse?>()
    val posicoesVeiculos: LiveData<PosicaoResponse?> = _posicoesVeiculos

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun carregarPosicoesVeiculos() {
        viewModelScope.launch {
            val result = getPosicoesVeiculosUseCase()
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