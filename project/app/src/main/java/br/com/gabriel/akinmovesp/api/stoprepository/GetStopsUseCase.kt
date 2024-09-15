package br.com.gabriel.akinmovesp.api.stoprepository

import br.com.gabriel.akinmovesp.api.models.stopmodel.StopModel
import javax.inject.Inject

class GetStopsUseCase @Inject constructor(
    private val paradaRepository: ParadaRepository
) {

    suspend operator fun invoke(termosBusca: String): Result<List<StopModel>> {
        return paradaRepository.getParadas(termosBusca)
    }
}