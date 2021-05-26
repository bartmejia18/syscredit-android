package com.example.syscredit.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.data.local.getFromSharedPreferences
import com.example.syscredit.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {

            getPermission()

            permission.observe(viewLifecycleOwner) {
                if (it.status == Status.SUCCESS) {
                    if (it.data?.acceso == "bmr") {
                        if (activity?.getFromSharedPreferences("logged_in", false) == true) {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
                        } else {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                        }
                    }
                }
            }
        }
    }
}