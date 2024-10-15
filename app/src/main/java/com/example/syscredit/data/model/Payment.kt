package com.example.syscredit.data.model

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("credito_id")
    val credito_id: Int = 0,
    @SerializedName("fecha_pago")
    val fecha_pago: String = "",
    @SerializedName("abono")
    val abono: Float = 0F,
    @SerializedName("estado")
    val estado: Int = 0,
)
