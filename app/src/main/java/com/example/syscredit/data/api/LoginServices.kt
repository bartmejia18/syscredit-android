package com.example.syscredit.data.api

import com.example.syscredit.data.model.User
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginServices {
    companion object {
        private const val LOGIN = "login"
    }
    @POST(LOGIN)
    suspend fun loginAsync(
        @Query("user") user: String,
        @Query("password") password: String
    ): Response<ApiResponse<User>>
}