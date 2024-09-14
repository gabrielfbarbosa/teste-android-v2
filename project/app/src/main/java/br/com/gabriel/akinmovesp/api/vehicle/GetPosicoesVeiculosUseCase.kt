package br.com.gabriel.akinmovesp.api.vehicle

import br.com.gabriel.akinmovesp.api.models.PosicaoResponse
import javax.inject.Inject

class GetPosicoesVeiculosUseCase @Inject constructor(
    private val veiculosRepository: VeiculosRepository
) {

    suspend operator fun invoke(): Result<PosicaoResponse> {
        return veiculosRepository.getPosicoesVeiculos()
    }
}