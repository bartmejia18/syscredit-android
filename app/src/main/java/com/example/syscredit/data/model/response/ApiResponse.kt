package com.example.syscredit.data.model.response

import com.squareup.moshi.Json

data class ApiResponse<T> (
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "result")
    val result: Boolean = false,
    @Json(name = "records")
    val records: T? = null,
)