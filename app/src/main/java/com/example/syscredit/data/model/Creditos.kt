package com.example.syscredit.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cliente(
    @SerializedName("nombre")
    val nombre: String = "",
    @SerializedName("apellido")
    val apellido: String = "",
    @SerializedName("direccion")
    val direccion: String = "",
    @SerializedName("telefono")
    val telefono: String = "",
    @SerializedName("dpi")
    val dpi: String = ""
) : Parcelable

@Parcelize
data class Credito(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("clientes_id")
    val clienteId: Int = 0,
    @SerializedName("planes_id")
    val planId: Int = 0,
    @SerializedName("montos_prestamo_id")
    val montoPrestamoId: Int = 0,
    @SerializedName("usuarios_creo")
    val usuarioCreadorId: Int = 0,
    @SerializedName("usuarios_cobrador")
    val usuarioCobradorId: Int = 0,
    @SerializedName("sucursal_id")
    val sucursalId: Int = 0,
    @SerializedName("saldo")
    val saldo: Float = 0F,
    @SerializedName("interes")
    val interes: Float = 0F,
    @SerializedName("deudatotal")
    val deudaTotal: Float = 0F,
    @SerializedName("cuota_diaria")
    val cuotaDiaria: Float = 0F,
    @SerializedName("cuota_minima")
    val cuotaMinima: Float = 0F,
    @SerializedName("fecha_inicio")
    val fechaInicio: String = "",
    @SerializedName("fecha_fin")
    val fechaFin: String = "",
    @SerializedName("estado")
    val estado: Int = 0,
    @SerializedName("cantidad_cuotas_pagadas")
    val cantidadCuotasPagadas: Long = 0,
    @SerializedName("monto_abonado")
    val montoAbonado: Long = 0,
    @SerializedName("fecha_limite")
    val fechaLimite: String = "",
    @SerializedName("pago_hoy")
    val pagoHoy: Boolean = false,
    @SerializedName("nombre_completo")
    val nombreCompleto: String = "",
    @SerializedName("cliente")
    val cliente: Cliente
) : Parcelable

@Parcelize
data class Records(
    @SerializedName("total_cobrar")
    val totalCobrar: Float = 0F,
    @SerializedName("total_minimo")
    val totalMinimo: Float = 0F,
    @SerializedName("registros")
    val registros: List<Credito> = listOf()
) : Parcelable

@Parcelize
data class Result(
    @SerializedName("records")
    val result: Records
): Parcelable