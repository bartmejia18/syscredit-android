package com.example.syscredit.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cliente(
    @Json(name = "nombre")
    val nombre: String = "",
    @Json(name = "apellido")
    val apellido: String = "",
    @Json(name = "direccion")
    val direccion: String = "",
    @Json(name = "telefono")
    val telefono: String = "",
    @Json(name = "dpi")
    val dpi: String = ""
): Parcelable

@Parcelize
data class Credito(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "clientes_id")
    val clientes_id: Int = 0,
    @Json(name = "usuarios_cobrador")
    val usuarios_cobrador: Int = 0,
    @Json(name = "saldo")
    val saldo: String? = null,
    @Json(name = "deudatotal")
    val deudatotal: String? = null,
    @Json(name = "cuota_diaria")
    val cuota_diaria: String? = null,
    @Json(name = "cuota_minima")
    val cuota_minima: String? = null,
    @Json(name = "fecha_inicio")
    val fecha_inicio: String? = null,
    @Json(name = "fecha_fin")
    val fecha_fin: String? = null,
    @Json(name = "estado")
    val estado: Int = 0,
    @Json(name = "cantidad_cuotas_pagadas")
    val cantidad_cuotas_pagadas: Int? = null,
    @Json(name = "cuotas_pendientes")
    val cuotas_pendientes: Int? = null,
    @Json(name = "cuotas_atrasadas")
    val cuotas_atrasadas: Int? = null,
    @Json(name = "estado_morosidad")
    val estado_morosidad: String = "",
    @Json(name = "monto_abonado")
    val monto_abonado: Long? = null,
    @Json(name = "pago_hoy")
    val pago_hoy: Boolean = false,
    @Json(name = "nombre_completo")
    val nombre_completo: String = "",
    @Json(name = "total_pagado")
    val total_pagado: String = "0",
    @Json(name = "tipo_plan")
    val tipo_plan: String = "",
    @Json(name = "cliente")
    val cliente: Cliente
) : Parcelable

data class Records(
    @Json(name = "total_cobrar")
    val total_cobrar: String? = null,
    @Json(name = "total_minimo")
    val total_minimo: String? = null,
    @Json(name = "total_cobrado")
    val total_cobrado: String? = null,
    @Json(name = "registros")
    val registros: List<Credito> = listOf()
)
