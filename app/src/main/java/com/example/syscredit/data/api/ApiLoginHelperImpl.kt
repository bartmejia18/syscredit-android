package com.example.syscredit.data.api

import com.example.syscredit.data.model.User
import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.services.LoginServices
import retrofit2.Response
import javax.inject.Inject

class ApiLoginHelperImpl @Inject constructor(
    private val apiServices: LoginServices
): ApiLoginHelper{
    override suspend fun login(user: String, password: String): Response<ApiResponse<User?>> = apiServices.loginAsync(user, password)
}