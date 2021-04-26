package com.example.syscredit.data.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "tipo_usuarios_id")
    val tipoUsuariosId: Int = 0,
    @Json(name = "sucursales_id")
    val sucursalesId: Int = 0,
    @Json(name = "nombre")
    val nombre: String = "",
    @Json(name = "user")
    val user: String = "",
    @Json(name = "estado")
    val estado: Int = 0
)
