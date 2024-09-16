package br.com.gabriel.akinmovesp.api.estimatedrepository

import br.com.gabriel.akinmovesp.api.models.estimatedmodel.EstimatedResponseModel
import javax.inject.Inject

class GetEstimatedUseCase @Inject constructor(
    private val previsaoRepository: EstimatedsRepository
) {
    suspend operator fun invoke(codigoParada: Int): Result<EstimatedResponseModel> {
        return previsaoRepository.getPrevisao(codigoParada)
    }
}