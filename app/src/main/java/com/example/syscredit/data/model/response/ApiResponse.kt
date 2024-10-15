package com.example.syscredit.data.model.response

import com.google.gson.annotations.SerializedName


data class ApiResponse<T> (
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("result")
    val result: Boolean = false,
    @SerializedName("records")
    val records: T? = null,
)