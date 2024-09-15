package br.com.gabriel.akinmovesp.api.models


data class Line(
    val c: String? = null,
    val cl: Int? = null,
    val lt0: String? = null,
    val lt1: String? = null,
    val qv: Int? = null,
    val sl: Int? = null,
    val vs: List<Vehicle>? = null
)