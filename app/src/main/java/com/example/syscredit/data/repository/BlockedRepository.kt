package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiBlockedHelper
import javax.inject.Inject

class BlockedRepository @Inject constructor(
    private val apiBlocked: ApiBlockedHelper
) {
    suspend fun getCustomerBlocked(branchId: Int) = apiBlocked.getCustomerBlocked(branchId)
}