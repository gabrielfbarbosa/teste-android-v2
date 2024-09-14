package br.com.gabriel.akinmovesp.api

import android.util.Log
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun authenticate(token: String): Boolean {
        return try {
            val response = api.authenticate(token)
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("AuthRepository", "Response code: ${response.code()}, body: ${response.body()}")
                body == true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}