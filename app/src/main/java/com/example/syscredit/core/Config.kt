package com.example.syscredit.core

import com.example.syscredit.BuildConfig

object AppConstants {
    var BASE_URL = when (BuildConfig.FLAVOR) {
        "credihelp" -> "http://157.230.9.234/ws/"
        "rapicredit" -> "http://206.189.68.122/ws/"
        else -> ""
    }
}
