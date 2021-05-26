package com.example.syscredit.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syscredit.data.model.Permission
import com.example.syscredit.data.repository.PermissionRepository
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val permissionRepository: PermissionRepository
) : ViewModel() {
    private val _permision = MutableLiveData<Resource<Permission>>()
    val permission: LiveData<Resource<Permission>>
        get() = _permision

    fun getPermission() {
        viewModelScope.launch {
            _permision.postValue(Resource.loading(null))
            permissionRepository.getPermission().let {
                if (it.isSuccessful) {
                    _permision.postValue(Resource.success(it.body()?.message, it.body()?.records))
                } else {
                    _permision.postValue(Resource.error(it.message()))
                }
            }
        }
    }
}