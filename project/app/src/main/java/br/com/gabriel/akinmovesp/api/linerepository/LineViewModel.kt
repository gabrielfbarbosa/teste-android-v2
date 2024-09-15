package br.com.gabriel.akinmovesp.api.linerepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabriel.akinmovesp.api.models.linemodel.LineModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LineViewModel @Inject constructor(
    private val getLinesUseCase: GetLinesUseCase
) : ViewModel() {

    private val _lines = MutableLiveData<List<LineModel>?>()
    val lines: LiveData<List<LineModel>?> = _lines

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun buscarLinhas(termosBusca: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getLinesUseCase(termosBusca)
            result.onSuccess { linhas ->
                _lines.value = linhas
                _isLoading.value = false
            }.onFailure { error ->
                _errorMessage.value = error.message
                _isLoading.value = false
            }
        }
    }
}
