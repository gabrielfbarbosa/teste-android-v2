package br.com.gabriel.akinmovesp.api.estimatedrepository

import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import br.com.gabriel.akinmovesp.api.models.estimatedmodel.EstimatedResponseModel
import javax.inject.Inject

class EstimatedsRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun getPrevisao(codigoParada: Int): Result<EstimatedResponseModel> {
        return try {
            val response = api.getEstimateds(codigoParada)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Resposta vazia em getPrevisao"))
                }
            } else {
                Result.failure(Exception("Erro na resposta em getPrevisao: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}