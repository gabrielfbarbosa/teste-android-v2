package br.com.gabriel.akinmovesp.api.stoprepository

import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import br.com.gabriel.akinmovesp.api.models.stopmodel.StopResponseModel
import javax.inject.Inject

class ParadaRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun getParadas(termosBusca: String): Result<List<StopResponseModel>> {
        return try {
            val response = api.getStops(termosBusca)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Resposta vazia em getParadas"))
                }
            } else {
                Result.failure(Exception("Erro na resposta em getParadas: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}