package com.example.syscredit.data.model

import com.google.gson.annotations.SerializedName

class Permission (
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("acceso")
    val acceso: String? = null
)