package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiLoginHelper
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiLoginHelper: ApiLoginHelper
) {
    suspend fun login(user: String, password: String) = apiLoginHelper.login(user, password)
}