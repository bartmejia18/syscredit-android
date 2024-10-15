package com.example.syscredit.data.api

import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.model.Blocked
import com.example.syscredit.data.services.BlockedServices
import retrofit2.Response
import javax.inject.Inject

class ApiBlockedHelperImpl @Inject constructor(
    private val apiBlocked: BlockedServices
) : ApiBlockedHelper {
    override suspend fun getCustomerBlocked(branchId: Int): Response<ApiResponse<List<Blocked>>> =
        apiBlocked.getCustomerBlocked(branchId)
}