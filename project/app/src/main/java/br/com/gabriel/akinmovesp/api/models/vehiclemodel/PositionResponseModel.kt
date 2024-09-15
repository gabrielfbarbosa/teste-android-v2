package br.com.gabriel.akinmovesp.api.models.vehiclemodel

data class PositionResponseModel(
    val hr: String? = null,
    val l: List<LinePositionModel>? = null
)