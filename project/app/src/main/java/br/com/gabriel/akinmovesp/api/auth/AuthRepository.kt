package br.com.gabriel.akinmovesp.api.auth

import android.util.Log
import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun authenticate(token: String): Boolean {
        return try {
            val response = api.authenticate(token)
            if (response.isSuccessful) {
                Log.d("AuthRepository", "Response code: ${response.code()}, body: ${response.body()}")
                response.body() == true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}