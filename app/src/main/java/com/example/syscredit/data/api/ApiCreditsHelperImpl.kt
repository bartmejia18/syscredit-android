package com.example.syscredit.data.api

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.response.ApiResponse
import com.example.syscredit.data.services.CreditsServices
import retrofit2.Response
import javax.inject.Inject

class ApiCreditsHelperImpl @Inject constructor(
    private val apiServices: CreditsServices
): ApiCreditsHelper {
    override suspend fun getListCredit(idUser: Int): Response<ApiResponse<Records>> = apiServices.getListCustomer(idUser)
}
