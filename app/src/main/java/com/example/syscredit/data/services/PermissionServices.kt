package com.example.syscredit.data.services

import com.example.syscredit.data.model.Permission
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface PermissionServices {
    @GET("movil/permiso")
    suspend fun getPermission(): Response<ApiResponse<Permission>>
}