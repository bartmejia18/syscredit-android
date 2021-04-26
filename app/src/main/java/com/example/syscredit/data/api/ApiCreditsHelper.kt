package com.example.syscredit.data.api

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.response.ApiResponse
import retrofit2.Response

interface ApiCreditsHelper {
    suspend fun getListCredit(idUser: Int): Response<ApiResponse<Records>>
}