package com.example.syscredit.data.model

import com.example.syscredit.data.model.Cliente
import com.google.gson.annotations.SerializedName

data class Blocked (
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("cliente_id")
    val customerId: Int = 0,
    @SerializedName("supervisor_id")
    val supervisorId: Int? = 0,
    @SerializedName("gerente_id")
    val gerenteId: Int? = 0,
    @SerializedName("razon")
    val razon: String? = "",
    @SerializedName("aprobacion_supervisor")
    val approvalSupervisor: Int? = 0,
    @SerializedName("comentario_supervisor")
    val commentSupervisor: String? = "",
    @SerializedName("fecha_supervisor")
    val dateSupervisor: String? = "",
    @SerializedName("aprobacion_gerente")
    val approvalGerente: Int? = 0,
    @SerializedName("comentario_gerente")
    val commentGerente: String? = "",
    @SerializedName("fecha_gerente")
    val dateGerente: String? = "",
    @SerializedName("estado")
    val status: Int? = 0,
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("nombre_supervisor")
    val supervisorName: String? = "",
    @SerializedName("nombre_gerente")
    val managerName: String? = "",
    @SerializedName("revisado")
    val reviewed: Boolean = false,
    @SerializedName("cliente")
    val customer: Cliente? = null
)