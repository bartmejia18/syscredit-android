package com.example.syscredit.data.services

import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface PaymentServices {
    companion object {
        private const val PAYMENT = "payments"
    }
    @POST(PAYMENT)
    suspend fun paymentAsync(
        @Query("idcredito") idcredito: Int,
        @Query("abono") abono: Double
    ): Response<ApiResponse<Any>>
}