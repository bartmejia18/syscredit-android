package com.example.syscredit.data.api

import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response

interface ApiPaymentHelper {
    suspend fun payment(idCredito: Int, abono: Double): Response<ApiResponse<Any>>
}