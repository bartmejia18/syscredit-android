package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiPaymentHelper
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val apiPaymentHelper: ApiPaymentHelper
) {
    suspend fun payment(idCredito: Int, abono: Double, origen: Int) = apiPaymentHelper.payment(idCredito, abono, origen)

    suspend fun getPayments(idCredito: Int) = apiPaymentHelper.getPayments(idCredito)
}