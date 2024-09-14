package br.com.gabriel.akinmovesp.api.vehicle

import android.util.Log
import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import br.com.gabriel.akinmovesp.api.models.PositionResponse
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun getPositionVehicle(): Result<PositionResponse> {
        return try {
            val response = api.getPositionVehicle()
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("VehicleRepository", "Response code: ${response.code()}, body: ${response.body()}")
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Falhou por algum motivo em VehicleRepository"))
                }
            } else {
                Result.failure(Exception("Erro na resposta em VehicleRepository: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}