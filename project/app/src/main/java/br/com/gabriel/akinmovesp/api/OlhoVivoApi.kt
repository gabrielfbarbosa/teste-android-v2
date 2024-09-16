package br.com.gabriel.akinmovesp.api

import br.com.gabriel.akinmovesp.api.models.estimatedmodel.EstimatedResponseModel
import br.com.gabriel.akinmovesp.api.models.linemodel.LineResponseModel
import br.com.gabriel.akinmovesp.api.models.stopmodel.StopResponseModel
import br.com.gabriel.akinmovesp.api.models.vehiclemodel.PositionResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OlhoVivoApi {

    @POST("Login/Autenticar")
    suspend fun authenticate (@Query("token") token: String): Response<Boolean>

    @GET("Posicao")
    suspend fun getPositionVehicle(): Response<PositionResponseModel>

    @GET("Linha/Buscar")
    suspend fun getLines(@Query("termosBusca") termosBusca: String): Response<List<LineResponseModel>>

    @GET("Parada/Buscar")
    suspend fun getStops(@Query("termosBusca") termosBusca: String): Response<List<StopResponseModel>>

    @GET("Previsao/Parada")
    suspend fun getEstimateds(
        @Query("codigoParada") codigoParada: Int
    ): Response<EstimatedResponseModel>
}

