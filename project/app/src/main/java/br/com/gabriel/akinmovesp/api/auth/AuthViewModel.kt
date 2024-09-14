package br.com.gabriel.akinmovesp.api.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _authResult = MutableLiveData<Boolean?>()
    val authResult: LiveData<Boolean?> = _authResult

    fun authenticate(token: String) {
        viewModelScope.launch {
            val result = authRepository.authenticate(token)
            _authResult.postValue(result)
        }
    }
}