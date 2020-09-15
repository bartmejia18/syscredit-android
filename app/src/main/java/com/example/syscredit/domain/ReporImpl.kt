package com.example.syscredit.domain

import com.example.syscredit.data.DataSource
import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.Result
import com.example.syscredit.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getCustomerList(): Resource<Result> {
        return dataSource.getCredito()
    }
}