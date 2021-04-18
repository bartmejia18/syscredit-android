package com.example.syscredit.data.restful

import com.example.syscredit.domain.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface CreditsServices {
    @GET("listaclientes")
    suspend fun getListCustomer(@Query("idusuario") userId:Int): Result
}