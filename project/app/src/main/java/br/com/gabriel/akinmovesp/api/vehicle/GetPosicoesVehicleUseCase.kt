package br.com.gabriel.akinmovesp.api.vehicle

import br.com.gabriel.akinmovesp.api.models.PositionResponse
import javax.inject.Inject

class GetPosicoesVehicleUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    suspend operator fun invoke(): Result<PositionResponse> {
        return vehicleRepository.getPositionVehicle()
    }
}