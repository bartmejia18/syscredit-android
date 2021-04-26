package com.example.syscredit.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syscredit.data.model.User
import com.example.syscredit.data.repository.LoginRepository
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _user = MutableLiveData<Resource<User>>()
    val user: LiveData<Resource<User>>
        get() = _user

    fun doLogin(user: String, password: String) {
        viewModelScope.launch {
            _user.postValue(Resource.loading(null))
            loginRepository.login(user, password).let {
                if (it.isSuccessful) {
                    _user.postValue(Resource.success(it.body()?.records?: User()))
                } else _user.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}