package br.com.gabriel.akinmovesp.api.linerepository

import br.com.gabriel.akinmovesp.api.models.linemodel.LineResponseModel
import javax.inject.Inject

class GetLinesUseCase @Inject constructor(
    private val lineRepository: LineRepository
) {

    suspend operator fun invoke(termosBusca: String): Result<List<LineResponseModel>> {
        return lineRepository.getLines(termosBusca)
    }
}