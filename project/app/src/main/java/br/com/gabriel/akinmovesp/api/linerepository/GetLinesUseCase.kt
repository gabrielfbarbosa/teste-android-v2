package br.com.gabriel.akinmovesp.api.linerepository

import br.com.gabriel.akinmovesp.api.models.linemodel.LineModel
import javax.inject.Inject

class GetLinesUseCase @Inject constructor(
    private val lineRepository: LineRepository
) {

    suspend operator fun invoke(termosBusca: String): Result<List<LineModel>> {
        return lineRepository.getLines(termosBusca)
    }
}