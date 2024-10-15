package com.example.syscredit.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("tipo_usuarios_id")
    var tipoUsuariosId: Int = 0,
    @SerializedName("sucursales_id")
    var sucursalesId: Int = 0,
    @SerializedName("nombre")
    var nombre: String = "",
    @SerializedName("user")
    var user: String = "",
    @SerializedName("estado")
    var estado: Int = 0
)
