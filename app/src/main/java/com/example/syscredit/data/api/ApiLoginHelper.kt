package com.example.syscredit.data.api

import com.example.syscredit.data.model.User
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response

interface ApiLoginHelper {
    suspend fun login(user: String, password: String): Response<ApiResponse<User>>
}