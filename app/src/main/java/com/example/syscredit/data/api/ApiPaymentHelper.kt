package com.example.syscredit.data.api

import com.example.syscredit.data.model.Payment
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response

interface ApiPaymentHelper {
    suspend fun payment(idCredito: Int, abono: Double, origen: Int): Response<ApiResponse<Any>>
    suspend fun getPayments(idCredito: Int): Response<ApiResponse<List<Payment>>>
}