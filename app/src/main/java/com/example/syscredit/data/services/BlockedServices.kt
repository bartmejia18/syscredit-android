package com.example.syscredit.data.services

import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.model.Blocked
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockedServices {
    @GET("clientUnlock")
    suspend fun getCustomerBlocked(
        @Query("branchId") branchId: Int,
    ): Response<ApiResponse<List<Blocked>>>
}