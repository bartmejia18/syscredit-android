package com.example.syscredit.data.model

import com.squareup.moshi.Json

class Permission (
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "acceso")
    val acceso: String? = null
)