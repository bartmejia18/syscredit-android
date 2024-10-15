package com.example.syscredit.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.core.extensions.DEFAULT_ANIMATION_DURATION_TIME
import com.example.syscredit.core.extensions.fadeOut
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.local.sharedPreferences
import com.example.syscredit.databinding.FragmentLoginBinding
import com.example.syscredit.utils.Status
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    companion object {
        const val COLLECTOR = 4
        const val SUPERVISOR = 5
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)

        with(binding) {
            loginButton.setOnClickListener {
                viewModel.doLogin(
                    emailTextInputLayout.editText?.text.toString(),
                    passwordTextInputLayout.editText?.text.toString()
                )
            }

            with(viewModel) {
                user.observe(viewLifecycleOwner) {
                    when (it.status) {
                        Status.LOADING -> {
                            loginButton.text = ""
                            progressBar.show()
                        }

                        Status.SUCCESS -> progressBar.fadeOut(
                            DEFAULT_ANIMATION_DURATION_TIME,
                            object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    if (it.data?.id != 0) {
                                        loginButton.text = getString(R.string.label_sign_in)
                                        activity?.sharedPreferences {
                                            putBoolean("logged_in", true)
                                            putString("name", it.data?.nombre)
                                            putInt("id", it.data?.id ?: 0)
                                            putInt("branchId", it.data?.sucursalesId ?: 0)
                                            putInt("tipoUsuariosId", it.data?.tipoUsuariosId ?: 0)
                                        }
                                        when (it.data?.tipoUsuariosId) {
                                            COLLECTOR -> findNavController().navigate(
                                                LoginFragmentDirections.actionLoginFragmentToMainFragment()
                                            )

                                            /*SUPERVISOR -> findNavController().navigate(
                                                LoginFragmentDirections.actionLoginFragmentToUnlocksFragment()
                                            )*/
                                        }

                                    } else {
                                        MaterialAlertDialogBuilder(
                                            requireContext(),
                                            com.google.android.material.R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                        )
                                            .setTitle(R.string.title_error_login)
                                            .setMessage(R.string.text_login_error)
                                            .setPositiveButton(R.string.action_accept) { dialog, _ -> dialog.dismiss() }
                                            .create().show()
                                    }
                                }
                            })

                        Status.ERROR -> progressBar.fadeOut(
                            DEFAULT_ANIMATION_DURATION_TIME,
                            object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    loginButton.text = getString(R.string.label_sign_in)
                                    MaterialAlertDialogBuilder(
                                        requireContext(),
                                        com.google.android.material.R.style.ThemeOverlay_AppCompat_Dialog_Alert
                                    )
                                        .setTitle(R.string.title_error_login)
                                        .setMessage(it.message)
                                        .setPositiveButton(R.string.action_accept) { dialog, _ -> dialog.dismiss() }
                                        .create().show()
                                }
                            })
                    }
                }
            }
        }
    }
}