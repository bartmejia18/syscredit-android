package com.example.syscredit.data.model

import com.squareup.moshi.Json

data class Payment(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "credito_id")
    val credito_id: Int = 0,
    @Json(name = "fecha_pago")
    val fecha_pago: String = "",
    @Json(name = "abono")
    val abono: Float = 0F,
    @Json(name = "estado")
    val estado: Int = 0,
)
