package br.com.gabriel.akinmovesp.api.stoprepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.stopmodel.StopModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopViewModel @Inject constructor(
    private val getStopsUseCase: GetStopsUseCase
) : ViewModel() {

    private val _paradas = MutableLiveData<List<StopModel>?>()
    val paradas: LiveData<List<StopModel>?> = _paradas

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun buscarParadas(termosBusca: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getStopsUseCase(termosBusca)
            result.onSuccess { paradasList ->
                _paradas.value = paradasList
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}