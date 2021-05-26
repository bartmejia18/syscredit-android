package com.example.syscredit.data.repository

import com.example.syscredit.data.api.ApiPermissionHelper
import javax.inject.Inject

class PermissionRepository @Inject constructor(
    private val apiPermissionHelper: ApiPermissionHelper
) {
    suspend fun getPermission() = apiPermissionHelper.getPermission()
}