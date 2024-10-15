package com.example.syscredit.data.api

import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.model.Blocked
import retrofit2.Response

interface ApiBlockedHelper {
    suspend fun getCustomerBlocked(branchId: Int): Response<ApiResponse<List<Blocked>>>
}