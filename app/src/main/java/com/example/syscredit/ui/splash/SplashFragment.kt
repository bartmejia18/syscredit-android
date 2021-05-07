package com.example.syscredit.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.data.local.getFromSharedPreferences
import dagger.hilt.EntryPoint

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity?.getFromSharedPreferences("logged_in", false) == true) {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
        } else {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }
    }
}