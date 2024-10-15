package com.example.syscredit.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
): Parcelable