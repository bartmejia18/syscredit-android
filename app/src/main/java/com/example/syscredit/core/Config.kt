package com.example.syscredit.core

import com.example.syscredit.BuildConfig

object AppConstants {
    var BASE_URL = when (BuildConfig.FLAVOR) {
        "credihelp" -> "http://157.230.9.234/ws/"
        "rapicredit" -> "http://144.126.216.191/ws/"
        "syscredit" -> "http://207.154.229.193/ws/"
        else -> ""
    }
}
