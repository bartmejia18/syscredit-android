package com.example.syscredit.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planes(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("descripcion")
    val descripcion: String = "",
    @SerializedName("tipo")
    val tipo: Int = 0,
    @SerializedName("domingo")
    val domingo: String = ""
): Parcelable

@Parcelize
data class Credito(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("clientes_id")
    val clientes_id: Int = 0,
    @SerializedName("usuarios_cobrador")
    val usuarios_cobrador: Int = 0,
    @SerializedName("saldo")
    val saldo: String? = null,
    @SerializedName("deudatotal")
    val deudatotal: String? = null,
    @SerializedName("cuota_diaria")
    val cuota_diaria: String? = null,
    @SerializedName("cuota_minima")
    val cuota_minima: String? = null,
    @SerializedName("dia_pago")
    val dia_pago: String? = "",
    @SerializedName("fecha_inicio")
    val fecha_inicio: String? = null,
    @SerializedName("fecha_fin")
    val fecha_fin: String? = null,
    @SerializedName("estado")
    val estado: Int = 0,
    @SerializedName("cantidad_cuotas_pagadas")
    val cantidad_cuotas_pagadas: Int? = null,
    @SerializedName("cuotas_pendientes")
    val cuotas_pendientes: Int? = null,
    @SerializedName("cuotas_atrasadas")
    val cuotas_atrasadas: Int? = null,
    @SerializedName("monto_abonado")
    val monto_abonado: Long? = null,
    @SerializedName("pago_hoy")
    val pago_hoy: Boolean = false,
    @SerializedName("nombre_completo")
    val nombre_completo: String? = "",
    @SerializedName("total_pagado")
    val total_pagado: String? = "0",
    @SerializedName("cliente")
    val cliente: Cliente,
    @SerializedName("planes")
    val planes: Planes? = null
) : Parcelable

data class Records(
    @SerializedName("total_cobrar")
    val total_cobrar: String? = null,
    @SerializedName("total_minimo")
    val total_minimo: String? = null,
    @SerializedName("total_cobrado")
    val total_cobrado: String? = null,
    @SerializedName("registros")
    val registros: List<Credito> = listOf()
)
