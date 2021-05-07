package com.example.syscredit.data.services

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CreditsServices {
    @GET("movil/listaclientes")
    suspend fun getListCustomer(
        @Query("idusuario") userId:Int
    ): Response<ApiResponse<Records>>
}