package br.com.gabriel.akinmovesp.api.estimatedrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.estimatedmodel.EstimatedResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstimatedViewModel @Inject constructor(
    private val getPrevisaoUseCase: GetEstimatedUseCase
) : ViewModel() {

    private val _estimated = MutableLiveData<EstimatedResponseModel?>()
    val estimated: LiveData<EstimatedResponseModel?> = _estimated

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun getEstimated(stopCode: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            val result = getPrevisaoUseCase(stopCode)
            result.onSuccess { estimatedResponse ->
                _estimated.value = estimatedResponse
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}