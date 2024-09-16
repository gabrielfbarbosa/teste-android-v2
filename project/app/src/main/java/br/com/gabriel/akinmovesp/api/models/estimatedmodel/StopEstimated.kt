package br.com.gabriel.akinmovesp.api.models.estimatedmodel

data class StopEstimated(
    val cp: Int? = null,
    val np: String? = null,
    val px: Double? = null,
    val py: Double? = null,
    val l: List<LineEstimated?>? = null
)