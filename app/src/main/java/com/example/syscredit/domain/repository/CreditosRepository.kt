package com.example.syscredit.domain.repository

import com.example.syscredit.domain.model.Credito
import com.example.syscredit.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CreditosRepository {
    suspend fun getListCredit(): Flow<Resource<List<Credito>>>
}