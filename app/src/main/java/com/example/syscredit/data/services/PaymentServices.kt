package com.example.syscredit.data.services

import com.example.syscredit.data.model.Payment
import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PaymentServices {
    companion object {
        private const val PAYMENT = "payments"
        private const val GET_PAYMENTS = "movil/getHistoryPayments"
    }
    @POST(PAYMENT)
    suspend fun paymentAsync(
        @Query("idcredito") idcredito: Int,
        @Query("abono") abono: Double
    ): Response<ApiResponse<Any>>

    @GET(GET_PAYMENTS)
    suspend fun getPaymentsAsync(
        @Query("credito_id") creditoId: Int
    ): Response<ApiResponse<List<Payment>>>
}