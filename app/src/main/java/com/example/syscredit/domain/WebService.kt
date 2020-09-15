package com.example.syscredit.domain

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("listaclientes")
    suspend fun getListCustomer(@Query("idusuario") userId:Int): Result
}