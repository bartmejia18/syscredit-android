package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiCreditsHelper
import com.example.syscredit.data.model.Credito
import com.example.syscredit.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreditsRepository @Inject constructor(
    private val apiCreditsHelper: ApiCreditsHelper
) {
    suspend fun getListCredits(idUser: Int) = apiCreditsHelper.getListCredit(idUser)
}