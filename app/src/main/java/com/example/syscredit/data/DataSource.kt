package com.example.syscredit.data

import com.example.syscredit.data.model.Records
import com.example.syscredit.data.model.Result
import com.example.syscredit.vo.Resource
import com.example.syscredit.vo.RetrofitClient

class DataSource {

    suspend fun getCredito(): Resource<Result>{
        return Resource.Success(RetrofitClient.webService.getListCustomer(46))
    }
}