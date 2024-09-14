package br.com.gabriel.akinmovesp.api.vehicle

import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import br.com.gabriel.akinmovesp.api.models.PosicaoResponse
import javax.inject.Inject

class VeiculosRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun getPosicoesVeiculos(): Result<PosicaoResponse> {
        return try {
            val response = api.getPosicoesVeiculos()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Resposta vazia"))
                }
            } else {
                Result.failure(Exception("Erro na resposta: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}