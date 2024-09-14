package br.com.gabriel.akinmovesp.api.models

import com.squareup.moshi.Json

data class PosicaoResponse(
    val hr: String,
    val l: List<Linha>
)

data class Linha(
    val c: String,
    val cl: Int,
    val sl: Int,
    val lt0: String,
    val lt1: String,
    val qv: Int,
    val vs: List<Veiculo>
)

data class Veiculo(
    val p: Int,
    val a: Boolean,
    val ta: String,
    val py: Double,
    val px: Double,
    val sv: Any?,
    @Json(name = "is") val is_: Any?
)
