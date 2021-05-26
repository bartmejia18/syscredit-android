package com.example.syscredit.data.api

import com.example.syscredit.data.model.Permission
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response

interface ApiPermissionHelper {
    suspend fun getPermission(): Response<ApiResponse<Permission>>
}