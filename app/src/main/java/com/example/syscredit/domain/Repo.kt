package com.example.syscredit.domain

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.Result
import com.example.syscredit.vo.Resource

interface Repo {
    suspend fun getCustomerList(): Resource<Result>
}