package com.example.syscredit.data.api

import com.example.syscredit.data.model.Payment
import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.services.PaymentServices
import retrofit2.Response
import javax.inject.Inject

class ApiPaymentHelperImpl @Inject constructor(
    private val apiServices: PaymentServices
): ApiPaymentHelper {
    override suspend fun payment(idCredito: Int, abono: Double, origen: Int): Response<ApiResponse<Any>> = apiServices.paymentAsync(idCredito, abono, origen)

    override suspend fun getPayments(idCredito: Int): Response<ApiResponse<List<Payment>>> = apiServices.getPaymentsAsync(idCredito)
}