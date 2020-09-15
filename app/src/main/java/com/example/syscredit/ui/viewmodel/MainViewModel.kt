package com.example.syscredit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.syscredit.domain.Repo
import com.example.syscredit.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {
    val fetchCustomerList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repo.getCustomerList())
        } catch (e:Exception) {
            emit(Resource.Failure(e))
        }
    }
}