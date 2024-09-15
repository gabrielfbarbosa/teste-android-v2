package br.com.gabriel.akinmovesp.api.linerepository

import br.com.gabriel.akinmovesp.api.OlhoVivoApi
import br.com.gabriel.akinmovesp.api.models.linemodel.LineModel
import javax.inject.Inject

class LineRepository @Inject constructor(
    private val api: OlhoVivoApi
) {

    suspend fun getLines(termosBusca: String): Result<List<LineModel>> {
        return try {
            val response = api.getLines(termosBusca)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Resposta vazia em getLines"))
                }
            } else {
                Result.failure(Exception("Erro na resposta em getLines: ${response.code()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}