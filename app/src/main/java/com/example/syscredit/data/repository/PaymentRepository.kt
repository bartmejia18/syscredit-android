package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiPaymentHelper
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val apiPaymentHelper: ApiPaymentHelper
) {
    suspend fun payment(idCredito: Int, abono: Double) = apiPaymentHelper.payment(idCredito, abono)
}