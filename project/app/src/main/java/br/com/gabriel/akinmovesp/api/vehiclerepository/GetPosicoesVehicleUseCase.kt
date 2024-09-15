package br.com.gabriel.akinmovesp.api.vehiclerepository

import br.com.gabriel.akinmovesp.api.models.vehiclemodel.PositionResponseModel
import javax.inject.Inject

class GetPosicoesVehicleUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository
) {

    suspend operator fun invoke(): Result<PositionResponseModel> {
        return vehicleRepository.getPositionVehicle()
    }
}