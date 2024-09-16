package br.com.gabriel.akinmovesp.api.stoprepository

import br.com.gabriel.akinmovesp.api.models.stopmodel.StopResponseModel
import javax.inject.Inject

class GetStopsUseCase @Inject constructor(
    private val paradaRepository: ParadaRepository
) {

    suspend operator fun invoke(termosBusca: String): Result<List<StopResponseModel>> {
        return paradaRepository.getParadas(termosBusca)
    }
}