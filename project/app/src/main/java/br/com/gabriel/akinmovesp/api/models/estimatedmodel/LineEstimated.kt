package br.com.gabriel.akinmovesp.api.models.estimatedmodel

data class LineEstimated(
    val c: String? = null,
    val cl: Int? = null,
    val sl: Int? = null,
    val lt0: String? = null,
    val lt1: String? = null,
    val qv: Int? = null,
    val vs: List<VehicleEstimated?>? = null
)