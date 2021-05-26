package com.example.syscredit.data.api

import com.example.syscredit.data.model.Permission
import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.services.PermissionServices
import retrofit2.Response
import javax.inject.Inject

class ApiPermissionHelperImpl @Inject constructor(
    private val apiServices: PermissionServices
): ApiPermissionHelper {
    override suspend fun getPermission(): Response<ApiResponse<Permission>> = apiServices.getPermission()
}